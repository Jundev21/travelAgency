package com.travelAgency.travelAgency.domain.hotel.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.travelAgency.travelAgency.domain.hotel.dto.HotelMapper;
import com.travelAgency.travelAgency.domain.hotel.dto.response.HotelsResponseDto;
import com.travelAgency.travelAgency.domain.hotel.entity.Hotels;
import com.travelAgency.travelAgency.domain.hotel.entity.HotelsImages;
import com.travelAgency.travelAgency.domain.hotel.repository.HotelRepository;
import com.travelAgency.travelAgency.domain.room.service.RoomService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class HotelService {

	private final HotelRepository hotelRepository;
	private final RoomService roomService;

	public ResponseEntity<List<HotelsResponseDto>> getAllHotels() {

		List<Hotels> hotels = hotelRepository.findAll();

		//해당 호텔에 맞는 최저가를 찾아야한다. 호텔 아이디를 넘겨야함.
		List<HotelsResponseDto> hotelsResponseDto = hotels.stream()
			.map(hotel -> {
				String lowestPrice = roomService.findLowestPrice(hotel.getId());
				return HotelMapper.INSTANCE.hotelResponseDto(hotel, lowestPrice);
			}).collect(Collectors.toList());

		return ResponseEntity.ok(hotelsResponseDto);


	}
}
