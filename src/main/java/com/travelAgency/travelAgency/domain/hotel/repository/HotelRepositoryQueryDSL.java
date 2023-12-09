package com.travelAgency.travelAgency.domain.hotel.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.travelAgency.travelAgency.domain.hotel.entity.Hotels;

public interface HotelRepositoryQueryDSL {

	List<Hotels> getCheckInCheckOutAddressCityTravelers(
		LocalDate checkIn,
		LocalDate checkOut,
		String city,
		String address,
		int travelers
	);

}
