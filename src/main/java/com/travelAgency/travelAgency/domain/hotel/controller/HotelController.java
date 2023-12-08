package com.travelAgency.travelAgency.domain.hotel.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travelAgency.travelAgency.domain.hotel.dto.response.HotelsResponseDto;
import com.travelAgency.travelAgency.domain.hotel.service.HotelService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class HotelController {

	private final HotelService hotelService;

	@GetMapping("/hotels")
	public ResponseEntity<List<HotelsResponseDto>> getAllHotels(
		@RequestParam(value = "pageNo", defaultValue = "0", required =false) int pageNo,
		@RequestParam(value = "pageSize", defaultValue = "10", required =false) int pageSize
	){

		return hotelService.getAllHotels( pageNo, pageSize);
	}



}
