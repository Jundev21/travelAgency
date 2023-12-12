package com.travelAgency.travelAgency.domain.reservation.dto.response;

import java.time.LocalDate;

import com.travelAgency.travelAgency.domain.room.dto.response.RoomResponseDto;
import com.travelAgency.travelAgency.domain.user.entity.Users;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReservationToEntity {
	private LocalDate checkIn;
	private LocalDate checkOut;
	private String name;
	private String phoneNumber;
	private String email;
	private String payStatus;
	private String reservationNumber;
	private Users users;
	private RoomResponseDto roomResponseDto;
}
