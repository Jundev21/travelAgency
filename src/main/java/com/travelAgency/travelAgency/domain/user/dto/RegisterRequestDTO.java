package com.travelAgency.travelAgency.domain.user.dto;

import com.travelAgency.travelAgency.domain.user.entity.Users;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegisterRequestDTO {

	private String name;
	private Integer age;
	private String email;
	private String password;

	public static RegisterRequestDTO fromEntity( Users users){

		return RegisterRequestDTO.builder()
			.name(users.getName())
			.age(users.getAge())
			.email(users.getEmail())
			.password(users.getPassword())
			.build();
	}

}
