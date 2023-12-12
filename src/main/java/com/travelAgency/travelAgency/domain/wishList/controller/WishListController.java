package com.travelAgency.travelAgency.domain.wishList.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelAgency.travelAgency.domain.wishList.service.WishListService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/wishLists")
@RequiredArgsConstructor
public class WishListController {

	private final WishListService wishListService;
	@PostMapping
	public ResponseEntity<String> addWishList(){
		return wishListService.addWishList();
	}

	@DeleteMapping("hotel/{hotelId}")
	public ResponseEntity<String> deleteWishList(
		@PathVariable long hotelId
	){
		return wishListService.deleteWishList(hotelId);
	}
}
