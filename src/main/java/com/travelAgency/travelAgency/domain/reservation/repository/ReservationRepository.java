package com.travelAgency.travelAgency.domain.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travelAgency.travelAgency.domain.reservation.entity.Reservations;


@Repository
public interface ReservationRepository extends JpaRepository<Reservations,Long> {
}
