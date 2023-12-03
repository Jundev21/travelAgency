package com.travelAgency.travelAgency.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegisterResponseDTO {

	private final String jwtToken;
}
