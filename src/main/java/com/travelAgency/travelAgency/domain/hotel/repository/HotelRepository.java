package com.travelAgency.travelAgency.domain.hotel.repository;

import com.travelAgency.travelAgency.domain.hotel.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HotelRepository extends JpaRepository<Hotel,Long> {
}
