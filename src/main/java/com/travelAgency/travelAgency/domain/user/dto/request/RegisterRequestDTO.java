package com.travelAgency.travelAgency.domain.user.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegisterRequestDTO {

	private String name;
	private int age;
	private String email;
	private String password;
}
