package com.travelAgency.travelAgency.domain.room.dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.travelAgency.travelAgency.domain.room.dto.response.RoomResponseDto;
import com.travelAgency.travelAgency.domain.room.dto.response.RoomResponseForReservationDto;
import com.travelAgency.travelAgency.domain.room.entity.Rooms;

@Mapper(componentModel = "spring")
public interface RoomMapper {

	RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);

	RoomResponseDto roomResponseDto(Rooms rooms);

	RoomResponseForReservationDto roomResponseForReservationDto(Rooms rooms);


}
