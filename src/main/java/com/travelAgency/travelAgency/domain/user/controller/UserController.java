package com.travelAgency.travelAgency.domain.user.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.travelAgency.travelAgency.domain.user.dto.request.LoginRequestDTO;
import com.travelAgency.travelAgency.domain.user.dto.request.RegisterRequestDTO;
import com.travelAgency.travelAgency.domain.user.dto.response.LoginResponseDTO;
import com.travelAgency.travelAgency.domain.user.dto.response.RegisterResponseDTO;
import com.travelAgency.travelAgency.domain.user.entity.Users;
import com.travelAgency.travelAgency.domain.user.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;

	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> loginMember(
		@Validated
		@RequestBody LoginRequestDTO loginRequest
	) {
		return userService.loginMember(loginRequest);
	}

	@PostMapping("/register")
	public ResponseEntity<RegisterResponseDTO> registerMember(
		@Validated
		@RequestBody RegisterRequestDTO registerRequest
	) {
		return userService.registerMember(registerRequest);
	}

	@PostMapping("/refresh-token")
	public LoginResponseDTO refreshToken(
		HttpServletRequest request,
		HttpServletResponse response
	) throws IOException {
		return userService.refreshToken(request, response);
	}

	@PostMapping("/logout")
	public ResponseEntity<String> logOutMember(
		@RequestBody RegisterRequestDTO registerRequest
	) {
		return ResponseEntity.ok("로그아웃 되었습니다");
	}

	@GetMapping("/user")
	public ResponseEntity<String> userInfo(
		@RequestBody RegisterRequestDTO registerRequest
	) {
		return ResponseEntity.ok("전송되었음");
	}

}
