package com.travelAgency.travelAgency.domain.hotel.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.travelAgency.travelAgency.domain.common.ErrorCode;
import com.travelAgency.travelAgency.domain.common.NormalException;
import com.travelAgency.travelAgency.domain.hotel.dto.HotelMapper;
import com.travelAgency.travelAgency.domain.hotel.dto.response.HotelsDetailsResponseDto;
import com.travelAgency.travelAgency.domain.hotel.dto.response.HotelsResponseDto;
import com.travelAgency.travelAgency.domain.hotel.entity.Hotels;
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

	public ResponseEntity<List<HotelsResponseDto>> getAllHotels(int pageNo, int pageSize) {

		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC));
		Page<Hotels> hotels = hotelRepository.findAll(pageable);
		List<Hotels> listOfHotels = hotels.getContent();

		//해당 호텔에 맞는 최저가를 찾아야한다. 호텔 아이디를 넘겨야함.
		List<HotelsResponseDto> hotelsResponseDto = listOfHotels.stream()
			.map(hotel ->
				HotelMapper.INSTANCE.hotelResponseDto(
					hotel,
					roomService.findLowestPrice(hotel.getId()),
					hotels.getNumber(),
					hotels.getSize(),
					hotels.getTotalElements(),
					hotels.getTotalPages(),
					hotels.isLast()
				)
			).collect(Collectors.toList());

		return ResponseEntity.ok(hotelsResponseDto);

	}

	public ResponseEntity<HotelsDetailsResponseDto> getHotelDetailsInfo(long hotelsId) {
		Hotels hotel = hotelRepository.findById(hotelsId).orElseThrow(() -> new NormalException(ErrorCode.NO_HOTEL_ID));
		HotelsDetailsResponseDto hotelsDetailsResponseDto = HotelMapper.INSTANCE.hotelDetailsResponseDto(hotel);
		return ResponseEntity.ok(hotelsDetailsResponseDto);
	}
}
