package com.travelAgency.travelAgency.domain.payment.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PaymentRequestDto {
	private String payMethod;
	private Integer totalPrice;
}
