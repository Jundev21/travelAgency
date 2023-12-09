package com.travelAgency.travelAgency.domain.hotel.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travelAgency.travelAgency.domain.hotel.dto.response.HotelsDetailsResponseDto;
import com.travelAgency.travelAgency.domain.hotel.dto.response.HotelsResponseDto;
import com.travelAgency.travelAgency.domain.hotel.service.HotelService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class HotelController {

	private final HotelService hotelService;



	//두가지 방법으로 검색해야한다.
	//만약 체크인 체크아웃 쿼리가 없을 경우에는 그냥 모든 호텔의 정보를 리턴하도록 해야하고
	//그렇지 않을 경우, 즉 체크인 체크아웃 인원 쿼리가 들어가있을경우에는 해당 조건에 맞는 정보를 검색하여 리턴해야한다.
	@GetMapping("/hotels")
	public ResponseEntity<List<HotelsResponseDto>> getAllHotels(
		@RequestParam(value = "pageNo", defaultValue = "0", required =false) int pageNo,
		@RequestParam(value = "pageSize", defaultValue = "10", required =false) int pageSize
	){
		return hotelService.getAllHotels( pageNo, pageSize);
	}

	@GetMapping("/hotels/{hotelsId}")
	public ResponseEntity<HotelsDetailsResponseDto> getHotelDetailsInfo(
		@PathVariable long hotelsId
	){
		return hotelService.getHotelDetailsInfo(hotelsId);
	}



}
