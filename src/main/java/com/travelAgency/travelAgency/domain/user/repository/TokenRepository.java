package com.travelAgency.travelAgency.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travelAgency.travelAgency.domain.user.entity.Tokens;

public interface TokenRepository extends JpaRepository<Tokens,Long> {
}
