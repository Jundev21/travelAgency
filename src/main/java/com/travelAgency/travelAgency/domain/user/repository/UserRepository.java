package com.travelAgency.travelAgency.domain.user.repository;

import java.util.Optional;

import com.travelAgency.travelAgency.domain.user.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<Users,Long> {

	Optional<Users> findByEmail(String userEmail);
}
