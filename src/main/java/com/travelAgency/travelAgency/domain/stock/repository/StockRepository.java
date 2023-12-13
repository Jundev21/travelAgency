package com.travelAgency.travelAgency.domain.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travelAgency.travelAgency.domain.room.entity.Rooms;
import com.travelAgency.travelAgency.domain.stock.entity.Stocks;

public interface StockRepository extends JpaRepository<Stocks,Long> {

	Stocks findByRooms(Rooms room);
}
