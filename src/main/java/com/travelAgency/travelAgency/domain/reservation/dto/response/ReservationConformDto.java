package com.travelAgency.travelAgency.domain.reservation.dto.response;

import java.time.LocalDate;

import com.travelAgency.travelAgency.domain.hotel.dto.response.HotelsResponseForReservationDto;
import com.travelAgency.travelAgency.domain.room.dto.response.RoomResponseForReservationDto;
import com.travelAgency.travelAgency.domain.user.entity.Users;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReservationConformDto {
	private LocalDate checkIn;
	private LocalDate checkOut;
	private String name;
	private String phoneNumber;
	private String email;
	private String payStatus;
	private String reservationNumber;
	private RoomResponseForReservationDto room;
	private HotelsResponseForReservationDto hotel;


}
