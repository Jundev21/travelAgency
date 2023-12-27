package com.travelAgency.travelAgency.domain.wishList.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travelAgency.travelAgency.domain.hotel.entity.Hotels;
import com.travelAgency.travelAgency.domain.user.entity.Users;
import com.travelAgency.travelAgency.domain.wishList.entity.WishLists;

@Repository
public interface WishListRepository extends JpaRepository<WishLists,Long> {

	Boolean  existsByUsersAndHotels(Users users, Hotels hotels);

	void deleteByUsersAndHotels(Users users, Hotels hotels);

	List<WishLists> findAllByUsers(Users users);
}
