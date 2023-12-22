package com.travelAgency.travelAgency.domain.hotel.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travelAgency.travelAgency.domain.hotel.dto.response.HotelsDetailsResponseDto;
import com.travelAgency.travelAgency.domain.hotel.dto.response.HotelsResponseDto;
import com.travelAgency.travelAgency.domain.hotel.service.HotelService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class HotelController {

	private final HotelService hotelService;

	// 두가지 방법으로 검색해야한다.
	// 만약 체크인 체크아웃 쿼리가 없을 경우에는 그냥 모든 호텔의 정보를 리턴하도록 해야하고
	// 그렇지 않을 경우, 즉 체크인 체크아웃 인원 쿼리가 들어가있을경우에는 해당 조건에 맞는 정보를 검색하여 리턴해야한다.

	@Operation(summary = "숙소 검색")
	@ApiResponses(value = {
		@ApiResponse(
			description = "정상적으로 호출했을 때",
			responseCode = "200"
		),
		@ApiResponse(
			description = "잘못된 형식으로 요청했을 때",
			responseCode = "400"

		),
		@ApiResponse(
			description = "서버 에러",
			responseCode = "500"
		)
	})
	@GetMapping(path = "/hotels")
	public ResponseEntity<List<HotelsResponseDto>> getHotels(
		@RequestParam(value = "pageNo", defaultValue = "0", required =false) int pageNo,
		@RequestParam(value = "pageSize", defaultValue = "10", required =false) int pageSize,
		@Nullable @RequestParam(value = "checkIn",  required =false) @DateTimeFormat(pattern = "yyyy-mm-dd") LocalDate checkIn,
		@Nullable @RequestParam(value = "checkOut",  required =false) @DateTimeFormat(pattern = "yyyy-mm-dd") LocalDate checkOut,
		@Nullable @RequestParam(value = "city",  required =false) String city,
		@Nullable @RequestParam(value = "address",  required =false) String address,
		@Nullable @RequestParam(value = "travers",  required =false) Integer travelers
		) {

		return hotelService.getHotels(pageNo,pageSize, checkIn, checkOut, city, address, travelers);
	}

	@GetMapping("/hotels/{hotelsId}")
	public ResponseEntity<HotelsDetailsResponseDto> getHotelDetailsInfo(
		@PathVariable long hotelsId
	){
		return hotelService.getHotelDetailsInfo(hotelsId);
	}



}
