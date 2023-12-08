package com.travelAgency.travelAgency.domain.room.service;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.travelAgency.travelAgency.domain.room.entity.Rooms;
import com.travelAgency.travelAgency.domain.room.repository.RoomRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class RoomService {

	private final RoomRepository roomRepository;


	public String findLowestPrice(Long hotelId){
		// 룸 최저가가 각 호텔에 맞는 최저가를 리턴해준다.
		return roomRepository.getLowestPrice(hotelId);
	}
}
