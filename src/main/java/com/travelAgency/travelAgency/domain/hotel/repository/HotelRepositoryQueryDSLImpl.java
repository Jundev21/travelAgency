package com.travelAgency.travelAgency.domain.hotel.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.travelAgency.travelAgency.domain.hotel.entity.Hotels;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class HotelRepositoryQueryDSLImpl implements HotelRepositoryQueryDSL{

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<Hotels> getCheckInCheckOutAddressCityTravelers(LocalDate checkIn, LocalDate checkOut, String city,
		String address, int travelers) {
		return null;
	}
}
