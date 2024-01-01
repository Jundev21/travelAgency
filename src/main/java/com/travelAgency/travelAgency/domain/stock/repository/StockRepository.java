package com.travelAgency.travelAgency.domain.stock.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travelAgency.travelAgency.domain.room.entity.Rooms;
import com.travelAgency.travelAgency.domain.stock.entity.Stocks;

public interface StockRepository extends JpaRepository<Stocks,Long> {

	Optional<List<Stocks>> findByRooms(Rooms room);
}
