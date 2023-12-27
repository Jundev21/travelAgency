package com.travelAgency.travelAgency.domain.wishList.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelAgency.travelAgency.domain.user.entity.Users;
import com.travelAgency.travelAgency.domain.wishList.dto.response.WishListResponse;
import com.travelAgency.travelAgency.domain.wishList.service.WishListServices;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/wishLists")
@RequiredArgsConstructor
public class WishListController {

	private final WishListServices wishListService;


	@GetMapping("/hotel/{hotelId}")
	public ResponseEntity<List<WishListResponse>> getWishList(
		@AuthenticationPrincipal Users users,
		@PathVariable long hotelId
	){
		return wishListService.getWishList(users, hotelId);
	}

	@PostMapping("/hotel/{hotelId}")
	public ResponseEntity<String> addWishList(
		@AuthenticationPrincipal Users users,
		@PathVariable long hotelId
	){
		return wishListService.addWishList(users,hotelId);
	}

	@DeleteMapping("/hotel/{hotelId}")
	public ResponseEntity<String> deleteWishList(
		@AuthenticationPrincipal Users users,
		@PathVariable long hotelId
	){
		return wishListService.deleteWishList(users, hotelId);
	}
}
