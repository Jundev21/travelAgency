package com.travelAgency.travelAgency.domain.room.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.travelAgency.travelAgency.domain.room.dto.response.RoomResponseDto;
import com.travelAgency.travelAgency.domain.room.service.RoomService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RoomController {

	private final RoomService roomService;

	@GetMapping("/hotel/{hotelId}/room")
	public ResponseEntity<List<RoomResponseDto>> roomDetails(
		@PathVariable long hotelId
	){
		return roomService.roomDetails(hotelId);
	}
}
