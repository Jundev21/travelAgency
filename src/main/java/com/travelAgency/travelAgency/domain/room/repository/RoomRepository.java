package com.travelAgency.travelAgency.domain.room.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travelAgency.travelAgency.domain.room.entity.Rooms;

public interface RoomRepository extends JpaRepository<Rooms,Long>, RoomRepositoryQueryDSL {
}
