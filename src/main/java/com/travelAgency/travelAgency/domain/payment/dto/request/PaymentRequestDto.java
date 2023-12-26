package com.travelAgency.travelAgency.domain.payment.dto.request;

import com.travelAgency.travelAgency.domain.payment.payEnum.PayEnum;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PaymentRequestDto {
	private PayEnum payMethod;
	private Integer totalPrice;
}
