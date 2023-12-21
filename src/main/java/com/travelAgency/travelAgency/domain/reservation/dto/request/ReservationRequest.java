package com.travelAgency.travelAgency.domain.reservation.dto.request;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReservationRequest {
	private LocalDate checkIn;
	private LocalDate checkOut;
	private String name;
	private String phoneNumber;
	private String email;
	private int travelers;
}
