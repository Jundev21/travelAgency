package com.travelAgency.travelAgency.domain.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode implements ErrorCodeInterface {
	NO_HOTEL_ID("해당 호텔이 없습니다"),
	NO_ROOM_ID("해당 객실이 없습니다"),
	NO_USER_EMAIL("해당 사용자가 없습니다"),
	NO_REVIEW_ID("해당 리뷰가 없습니다."),
	ALREADY_REGISTER_USER("이미 등록한 회원입니다."),
	NO_ROOMS_STOCKS("해당 객실의 재고가 없습니다.")
	;
	private final String errorMessage;
	}
