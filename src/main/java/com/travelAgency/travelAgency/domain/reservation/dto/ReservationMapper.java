package com.travelAgency.travelAgency.domain.reservation.dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.travelAgency.travelAgency.domain.reservation.dto.response.ReservationToEntity;
import com.travelAgency.travelAgency.domain.reservation.entity.Reservations;
import com.travelAgency.travelAgency.domain.room.dto.response.RoomResponseDto;
import com.travelAgency.travelAgency.domain.room.entity.Rooms;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
	ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);
	ReservationToEntity reservationToEntity(Reservations reservations);


}
