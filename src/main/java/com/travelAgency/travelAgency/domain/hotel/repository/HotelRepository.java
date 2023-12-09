package com.travelAgency.travelAgency.domain.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travelAgency.travelAgency.domain.hotel.entity.Hotels;

public interface HotelRepository extends JpaRepository<Hotels,Long>, HotelRepositoryQueryDSL {
}
