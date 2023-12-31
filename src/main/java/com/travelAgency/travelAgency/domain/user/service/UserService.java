package com.travelAgency.travelAgency.domain.user.service;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travelAgency.travelAgency.common.error.ErrorCode;
import com.travelAgency.travelAgency.common.exception.NormalException;
import com.travelAgency.travelAgency.domain.user.entity.Tokens;
import com.travelAgency.travelAgency.domain.user.repository.TokenRepository;
import com.travelAgency.travelAgency.jwt.JwtService;
import com.travelAgency.travelAgency.domain.user.dto.UserMapper;
import com.travelAgency.travelAgency.domain.user.dto.request.LoginRequestDTO;
import com.travelAgency.travelAgency.domain.user.dto.request.RegisterRequestDTO;
import com.travelAgency.travelAgency.domain.user.dto.response.LoginResponseDTO;
import com.travelAgency.travelAgency.domain.user.dto.response.RegisterResponseDTO;
import com.travelAgency.travelAgency.domain.user.entity.Role;
import com.travelAgency.travelAgency.domain.user.entity.Users;
import com.travelAgency.travelAgency.domain.user.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final JwtService jwtService;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final TokenRepository tokenRepository;

	public ResponseEntity<RegisterResponseDTO> registerMember(RegisterRequestDTO registerRequest) {

		if(userRepository.existsByEmail(registerRequest.getEmail())){
			throw new NormalException(ErrorCode.ALREADY_REGISTER_USER);
		}

		Users newUsers = new Users(
			registerRequest.getName(),
			registerRequest.getAge(),
			registerRequest.getEmail(),
			passwordEncoder.encode(registerRequest.getPassword()),
			Role.USER
		);
		userRepository.save(newUsers);

		String jwtToken = jwtService.generateToken(newUsers);

		return ResponseEntity.ok(UserMapper.INSTANCE.registerResponseDto(jwtToken,"회원가입에 성공하였습니다."));

	}

	public ResponseEntity<LoginResponseDTO> loginMember(LoginRequestDTO loginRequest) {

		authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(
				loginRequest.getEmail(),
				loginRequest.getPassword()
			)
		);

		Users newUsers = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(()-> new RuntimeException("not user"));
		String jwtToken = jwtService.generateToken(newUsers);
		String jwtRefreshToken = jwtService.generateRefreshToken(newUsers);

		return ResponseEntity.ok(UserMapper.INSTANCE.logInResponseDto(jwtToken,jwtRefreshToken,"로그인에 성공하였습니다."));
	}

	public LoginResponseDTO refreshToken(
		HttpServletRequest request,
		HttpServletResponse response
	) throws IOException {
		final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		final String refreshToken;
		final String userEmail;
		if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
			return null;
		}
		refreshToken = authHeader.substring(7);
		userEmail = jwtService.extractUserName(refreshToken);
		if (userEmail != null) {
			Users user = userRepository.findByEmail(userEmail)
				.orElseThrow();
			if (jwtService.isTokenValid(refreshToken, user)) {
				String accessToken = jwtService.generateToken(user);
				saveUserToken(user, accessToken);
				return LoginResponseDTO.builder()
					.accessToken(accessToken)
					.refreshToken(refreshToken)
					.build();

			}
		}
		return null;
	}


	private void saveUserToken(Users user, String jwtToken) {
		var token = Tokens.builder()
			.user(user)
			.token(jwtToken)
			.expired(false)
			.revoked(false)
			.build();
		tokenRepository.save(token);
	}


}
