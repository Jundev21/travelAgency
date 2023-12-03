package com.travelAgency.travelAgency.domain.user.controller;

import com.travelAgency.travelAgency.domain.user.dto.LoginRequestDTO;
import com.travelAgency.travelAgency.domain.user.dto.LoginResponseDTO;
import com.travelAgency.travelAgency.domain.user.dto.RegisterRequestDTO;
import com.travelAgency.travelAgency.domain.user.dto.RegisterResponseDTO;
import com.travelAgency.travelAgency.domain.user.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;

	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> loginMember(
		@RequestBody @Validated LoginRequestDTO loginRequest
	) {

		return userService.loginMember(loginRequest);
	}

	@PostMapping("/register")
	public ResponseEntity<RegisterResponseDTO> registerMember(
		@RequestBody RegisterRequestDTO registerRequest
	) {

		return userService.registerMember(registerRequest);
	}
}
