package com.travelAgency.travelAgency.domain.visit.repository;

import com.travelAgency.travelAgency.domain.visit.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VisitRepository extends JpaRepository<Visit,Long> {
}
