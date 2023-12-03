package com.travelAgency.travelAgency.domain.user.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.travelAgency.travelAgency.domain.jwt.JwtService;
import com.travelAgency.travelAgency.domain.user.dto.LoginRequestDTO;
import com.travelAgency.travelAgency.domain.user.dto.LoginResponseDTO;
import com.travelAgency.travelAgency.domain.user.dto.RegisterRequestDTO;
import com.travelAgency.travelAgency.domain.user.dto.RegisterResponseDTO;
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
			passwordEncoder.encode(registerRequest.getPassword())
		);
		userRepository.save(newUsers);

		String jwtToken = jwtService.generateToken(newUsers);

		RegisterResponseDTO registerResponseDTO = RegisterResponseDTO.builder()
			.jwtToken(jwtToken)
			.build();

		return ResponseEntity.ok(registerResponseDTO);
	}

	public ResponseEntity<LoginResponseDTO> loginMember(LoginRequestDTO loginRequest) {
		System.out.println(loginRequest.getEmail() + " =================+");
		authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(
				loginRequest.getEmail(), loginRequest.getPassword()
			)
		);

		Users newUsers = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow();
		String jwtToken = jwtService.generateToken(newUsers);

		return ResponseEntity.ok(LoginResponseDTO.builder()
			.accessToken(jwtToken)
			.build());
	}
}
