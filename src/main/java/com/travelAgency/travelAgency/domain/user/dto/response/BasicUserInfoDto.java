package com.travelAgency.travelAgency.domain.user.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BasicUserInfoDto {


	private String name;
	private String email;
	private int age;
}
