package com.travelAgency.travelAgency.domain.payment.dto.response;

import java.util.List;

import com.travelAgency.travelAgency.domain.reservation.dto.response.ReservationToEntity;
import com.travelAgency.travelAgency.domain.reservation.entity.Reservations;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PaymentResponseDto {
	private String price;
	private String paymentMethod;
	private List<ReservationToEntity> reservationsList;

}
