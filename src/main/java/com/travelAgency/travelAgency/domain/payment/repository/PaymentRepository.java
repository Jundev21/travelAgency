package com.travelAgency.travelAgency.domain.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travelAgency.travelAgency.domain.payment.entity.Payments;

public interface PaymentRepository extends JpaRepository<Payments,Long> {
}
