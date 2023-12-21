package com.travelAgency.travelAgency.domain.reservation.reservationEnum;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ReservationEnum {
	failed("지불"),
	successed("결제 완료"),
	;
	private final String status;

}
