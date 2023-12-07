package com.travelAgency.travelAgency.domain.user.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.travelAgency.travelAgency.domain.jwt.JwtService;
import com.travelAgency.travelAgency.domain.user.dto.UserMapper;
import com.travelAgency.travelAgency.domain.user.dto.request.LoginRequestDTO;
import com.travelAgency.travelAgency.domain.user.dto.request.RegisterRequestDTO;
import com.travelAgency.travelAgency.domain.user.dto.response.LoginResponseDTO;
import com.travelAgency.travelAgency.domain.user.dto.response.RegisterResponseDTO;
import com.travelAgency.travelAgency.domain.user.entity.Role;
import com.travelAgency.travelAgency.domain.user.entity.Users;
import com.travelAgency.travelAgency.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final JwtService jwtService;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;

	public ResponseEntity<RegisterResponseDTO> registerMember(RegisterRequestDTO registerRequest) {



		Users newUsers = new Users(
			registerRequest.getName(),
			registerRequest.getAge(),
			registerRequest.getEmail(),
			passwordEncoder.encode(registerRequest.getPassword()),
			Role.USER
		);
		userRepository.save(newUsers);

		String jwtToken = jwtService.generateToken(newUsers);

		return ResponseEntity.ok(UserMapper.INSTANCE.registerResponseDto(jwtToken));

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

		return ResponseEntity.ok(UserMapper.INSTANCE.logInResponseDto(jwtToken,null));
	}
}
