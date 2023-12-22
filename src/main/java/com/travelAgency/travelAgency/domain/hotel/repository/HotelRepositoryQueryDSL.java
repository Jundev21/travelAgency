package com.travelAgency.travelAgency.domain.hotel.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.travelAgency.travelAgency.domain.hotel.entity.Hotels;
import com.travelAgency.travelAgency.domain.room.entity.Rooms;
import com.travelAgency.travelAgency.domain.stock.entity.Stocks;

public interface HotelRepositoryQueryDSL {

	List<Hotels> getCheckInCheckOutAddressCityTravelers(
		LocalDate checkIn,
		LocalDate checkOut,
		String city,
		String address,
		int travelers,
		long offset,
		int pageSize
	);

	List<Rooms> getAvailableRooms(LocalDate checkIn, LocalDate checkOut, int travelers);
	List<Stocks> getStocksFilter(LocalDate checkIn, LocalDate checkOut);

}
