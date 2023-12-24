package com.travelAgency.travelAgency.domain.room.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.travelAgency.travelAgency.domain.hotel.entity.Hotels;
import com.travelAgency.travelAgency.domain.hotel.repository.HotelRepository;
import com.travelAgency.travelAgency.domain.room.dto.RoomMapper;
import com.travelAgency.travelAgency.domain.room.dto.response.RoomResponseDto;
import com.travelAgency.travelAgency.domain.room.repository.RoomRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class RoomService {

	private final RoomRepository roomRepository;
	private final HotelRepository hotelRepository;

	public ResponseEntity<List<RoomResponseDto>> roomDetails(long hotelId) {
		Hotels hotels = hotelRepository.findById(hotelId).orElseThrow();
		return ResponseEntity.ok(hotels.getRoomsList().stream()
				.map(RoomMapper.INSTANCE::roomResponseDto)
				.toList());
	}

	public int findLowestPrice(Long hotelId){
		// 룸 최저가가 각 호텔에 맞는 최저가를 리턴해준다.
		return roomRepository.getLowestPrice(hotelId);
	}
}
