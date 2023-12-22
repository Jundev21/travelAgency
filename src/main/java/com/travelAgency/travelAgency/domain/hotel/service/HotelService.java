package com.travelAgency.travelAgency.domain.hotel.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.travelAgency.travelAgency.common.error.ErrorCode;
import com.travelAgency.travelAgency.common.exception.NormalException;
import com.travelAgency.travelAgency.domain.hotel.dto.HotelMapper;
import com.travelAgency.travelAgency.domain.hotel.dto.response.HotelsDetailsResponseDto;
import com.travelAgency.travelAgency.domain.hotel.dto.response.HotelsResponseDto;
import com.travelAgency.travelAgency.domain.hotel.entity.Hotels;
import com.travelAgency.travelAgency.domain.hotel.repository.HotelRepository;
import com.travelAgency.travelAgency.domain.room.entity.Rooms;
import com.travelAgency.travelAgency.domain.room.repository.RoomRepository;
import com.travelAgency.travelAgency.domain.stock.entity.Stocks;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class HotelService {

	private final HotelRepository hotelRepository;
	private final RoomRepository roomRepository;

	public ResponseEntity<List<HotelsResponseDto>> getAllHotels(int pageNo, int pageSize) {

		// Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC));
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<Hotels> hotels = hotelRepository.findAll(pageable);
		List<Hotels> listOfHotels = hotels.getContent();

		//해당 호텔에 맞는 최저가를 찾아야한다. 호텔 아이디를 넘겨야함.
		List<HotelsResponseDto> hotelsResponseDto = listOfHotels.stream()
			.map(hotel -> HotelMapper.INSTANCE.hotelResponseDto(
					hotel,
					roomRepository.getLowestPrice(hotel.getId()),
					hotels.getNumber(),
					hotels.getSize(),
					hotels.getTotalElements(),
					hotels.getTotalPages(),
					hotels.isLast()
				)
			).collect(Collectors.toList());

		return ResponseEntity.ok(hotelsResponseDto);

	}

	public ResponseEntity<List<HotelsResponseDto>> getHotels(int pageNo, int pageSize, LocalDate checkIn,
		LocalDate checkOut, String city, String address, Integer travelers) {

		if (Objects.isNull(checkIn) && Objects.isNull(checkOut) && Objects.isNull(city) && Objects.isNull(address)
			&& Objects.isNull(travelers)) {
			return getAllHotels(pageNo, pageSize);
		}

		Pageable pageable = PageRequest.of(pageNo, pageSize);
		// Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC));
		List<Hotels> hotels = hotelRepository.getCheckInCheckOutAddressCityTravelers(
			checkIn,
			checkOut,
			city,
			address,
			travelers,
			pageable.getOffset(),
			pageable.getPageSize()

		);

		List<HotelsResponseDto> hotelsResponseDtoList = hotels.stream()
			.map(hotel -> HotelMapper.INSTANCE.hotelResponseDto(
				hotel,
				roomRepository.getLowestPrice(hotel.getId()),
				Long.valueOf(pageable.getOffset()).intValue(),
				pageable.getPageSize(),
				1,
				1,
				false
			)).toList();
		return ResponseEntity.ok(hotelsResponseDtoList);
	}

	public ResponseEntity<HotelsDetailsResponseDto> getHotelDetailsInfo(long hotelsId) {
		Hotels hotel = getHotel(hotelsId);
		HotelsDetailsResponseDto hotelsDetailsResponseDto = HotelMapper.INSTANCE.hotelDetailsResponseDto(hotel);
		return ResponseEntity.ok(hotelsDetailsResponseDto);
	}

	public Hotels getHotel(long hotelsId) {
		return hotelRepository.findById(hotelsId).orElseThrow(() -> new NormalException(ErrorCode.NO_HOTEL_ID));
	}

}
