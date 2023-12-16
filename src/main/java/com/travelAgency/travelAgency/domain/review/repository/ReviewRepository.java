package com.travelAgency.travelAgency.domain.review.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travelAgency.travelAgency.domain.hotel.entity.Hotels;
import com.travelAgency.travelAgency.domain.review.entity.Reviews;

public interface ReviewRepository extends JpaRepository<Reviews,Long> {

	Optional<List<Reviews>> findByHotels(Hotels hotels);
}
