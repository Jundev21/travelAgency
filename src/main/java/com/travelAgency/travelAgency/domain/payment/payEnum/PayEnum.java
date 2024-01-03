package com.travelAgency.travelAgency.domain.payment.payEnum;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PayEnum {

	StandBy("미지불"),
	Card("카드"),
	KakaoPay("카카오페이"),
	Account("계좌이체")
	;
	private final String method;

}
