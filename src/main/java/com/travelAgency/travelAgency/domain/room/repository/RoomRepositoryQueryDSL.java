package com.travelAgency.travelAgency.domain.room.repository;

import java.util.List;

import com.travelAgency.travelAgency.domain.room.entity.Rooms;

public interface RoomRepositoryQueryDSL {

	int getLowestPrice(Long hotelId);
}
