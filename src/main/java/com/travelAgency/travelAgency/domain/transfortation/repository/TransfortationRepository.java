package com.travelAgency.travelAgency.domain.transfortation.repository;

import com.travelAgency.travelAgency.domain.transfortation.entity.Transfortation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TransfortationRepository extends JpaRepository<Transfortation,Long> {
}
