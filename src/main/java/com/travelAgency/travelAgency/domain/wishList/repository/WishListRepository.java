package com.travelAgency.travelAgency.domain.wishList.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travelAgency.travelAgency.domain.wishList.entity.WishLists;

@Repository
public interface WishListRepository extends JpaRepository<WishLists,Long> {
}
