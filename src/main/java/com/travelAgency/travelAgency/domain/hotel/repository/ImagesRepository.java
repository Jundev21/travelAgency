package com.travelAgency.travelAgency.domain.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travelAgency.travelAgency.domain.hotel.entity.HotelsImages;

public interface ImagesRepository extends JpaRepository<HotelsImages,Long> {
}
