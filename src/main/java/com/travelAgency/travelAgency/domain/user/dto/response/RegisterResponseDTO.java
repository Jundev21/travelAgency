package com.travelAgency.travelAgency.domain.user.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegisterResponseDTO {
	private String jwtToken;
}
