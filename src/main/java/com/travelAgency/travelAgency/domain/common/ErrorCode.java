package com.travelAgency.travelAgency.domain.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
	NO_HOTEL_ID("해당 호텔이 없습니다");
	private final String message;
}
