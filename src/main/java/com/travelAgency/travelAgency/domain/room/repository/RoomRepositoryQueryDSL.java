package com.travelAgency.travelAgency.domain.room.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.travelAgency.travelAgency.domain.room.entity.Rooms;

public interface RoomRepositoryQueryDSL {

	int getLowestPrice(Long hotelId);
	Long checkAvailableRooms(LocalDate checkIn, LocalDate checkOut, int travelers, long targetRoomId);

}
