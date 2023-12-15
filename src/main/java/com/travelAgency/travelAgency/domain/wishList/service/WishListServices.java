package com.travelAgency.travelAgency.domain.wishList.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.travelAgency.travelAgency.domain.hotel.entity.Hotels;
import com.travelAgency.travelAgency.domain.hotel.repository.HotelRepository;
import com.travelAgency.travelAgency.domain.user.entity.Users;
import com.travelAgency.travelAgency.domain.user.repository.UserRepository;
import com.travelAgency.travelAgency.domain.wishList.dto.WishListWrapper;
import com.travelAgency.travelAgency.domain.wishList.entity.WishLists;
import com.travelAgency.travelAgency.domain.wishList.repository.WishListRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class WishListServices {

	private final WishListRepository wishListRepository;
	private final UserRepository userRepository;
	private final HotelRepository hotelRepository;

	public ResponseEntity<String> addWishList(Users users, long hotelId) {

		Users usersFindByEmail = userRepository.findByEmail(users.getEmail()).orElseThrow();
		Hotels hotelsFindById = hotelRepository.findById(hotelId).orElseThrow();

		if(wishListRepository.findByUsersAndHotels(usersFindByEmail,hotelsFindById)){
			throw new RuntimeException("이미 좋아요가 있습니다. ");
		}
		WishLists newWishLists = WishListWrapper.INSTANCE.wishListToEntity(usersFindByEmail,hotelsFindById);
		wishListRepository.save(newWishLists);
		return ResponseEntity.ok("좋아요가 추가되었습니다,");

	}

	public ResponseEntity<String> deleteWishList(Users users, long hotelId) {

		Users usersFindByEmail = userRepository.findByEmail(users.getEmail()).orElseThrow();
		Hotels hotelsFindById = hotelRepository.findById(hotelId).orElseThrow();

		if(!wishListRepository.findByUsersAndHotels(usersFindByEmail,hotelsFindById)){
			throw new RuntimeException("좋아요가 없습니다.");
		}

		wishListRepository.deleteByUsersAndHotels(usersFindByEmail, hotelsFindById);
		return ResponseEntity.ok("좋아요가 삭제되었습니다.");
	}
}
