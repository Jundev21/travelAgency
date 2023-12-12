package com.travelAgency.travelAgency.domain.wishList.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.travelAgency.travelAgency.domain.wishList.repository.WishListRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class WishListService {

	private final WishListRepository wishListRepository;

	public ResponseEntity<String> deleteWishList(long hotelId) {

		return null;

	}

	public ResponseEntity<String> addWishList() {
		return null;
	}
}
