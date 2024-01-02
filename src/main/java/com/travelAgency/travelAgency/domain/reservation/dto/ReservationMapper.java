package com.travelAgency.travelAgency.domain.reservation.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.travelAgency.travelAgency.domain.hotel.entity.Hotels;
import com.travelAgency.travelAgency.domain.payment.payEnum.PayEnum;
import com.travelAgency.travelAgency.domain.reservation.dto.request.ReservationRequest;
import com.travelAgency.travelAgency.domain.reservation.dto.response.ReservationConformDto;
import com.travelAgency.travelAgency.domain.reservation.dto.response.ReservationToEntity;
import com.travelAgency.travelAgency.domain.reservation.entity.Reservations;
import com.travelAgency.travelAgency.domain.room.dto.response.RoomResponseDto;
import com.travelAgency.travelAgency.domain.room.entity.Rooms;
import com.travelAgency.travelAgency.domain.user.entity.Users;

import jakarta.persistence.Enumerated;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
	ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);


	@Mapping(source = "reservations.rooms", target = "roomInfo")
	ReservationToEntity reservationToEntity(Reservations reservations);
	@Mapping(source = "reservationRequest.travelers", target = "travelers")
	@Mapping(source = "reservationRequest.name", target = "name")
	@Mapping(source = "reservationRequest.email", target = "email")
	Reservations reservationConstructToEntity(
		ReservationRequest reservationRequest,
		PayEnum payStatus,
		String reservationNumber,
		Rooms rooms,
		Users users
		);


	@Mapping(source = "hotels", target = "hotel")
	@Mapping(source = "rooms", target = "room")
	@Mapping(source = "reservation.name", target = "name")
	@Mapping(source = "reservation.email", target = "email")
	ReservationConformDto reservationConform(
		Reservations reservation,
		Hotels hotels,
		Rooms rooms
	);


}
