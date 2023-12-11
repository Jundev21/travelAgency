package com.travelAgency.travelAgency.domain.room.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.deser.BasicDeserializerFactory;
import com.travelAgency.travelAgency.domain.common.ErrorCode;
import com.travelAgency.travelAgency.domain.common.NormalException;
import com.travelAgency.travelAgency.domain.hotel.entity.Hotels;
import com.travelAgency.travelAgency.domain.hotel.repository.HotelRepository;
import com.travelAgency.travelAgency.domain.hotel.service.HotelService;
import com.travelAgency.travelAgency.domain.room.dto.RoomMapper;
import com.travelAgency.travelAgency.domain.room.dto.response.RoomResponseDto;
import com.travelAgency.travelAgency.domain.room.entity.Rooms;
import com.travelAgency.travelAgency.domain.room.repository.RoomRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class RoomService {

	private final RoomRepository roomRepository;
	private final HotelService hotelService;

	public ResponseEntity<List<RoomResponseDto>> roomDetails(long hotelId) {
		Hotels hotels = hotelService.getHotel(hotelId);
		return ResponseEntity.ok(hotels.getRoomsList().stream()
				.map(RoomMapper.INSTANCE::roomResponseDto)
				.toList());
	}

	public String findLowestPrice(Long hotelId){
		// 룸 최저가가 각 호텔에 맞는 최저가를 리턴해준다.
		return roomRepository.getLowestPrice(hotelId);
	}
}
