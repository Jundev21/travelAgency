package com.travelAgency.travelAgency.domain.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegisterRequestDTO {

	@NotBlank
	private String name;
	private int age;
	@NotBlank
	@Email
	private String email;
	@NotBlank
	private String password;
}
