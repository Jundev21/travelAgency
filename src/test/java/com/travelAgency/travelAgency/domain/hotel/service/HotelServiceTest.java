package com.travelAgency.travelAgency.domain.hotel.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;

import com.travelAgency.travelAgency.domain.hotel.dto.response.HotelsDetailsResponseDto;
import com.travelAgency.travelAgency.domain.hotel.dto.response.HotelsResponseDto;
import com.travelAgency.travelAgency.domain.hotel.entity.Hotels;
import com.travelAgency.travelAgency.domain.hotel.entity.HotelsImages;
import com.travelAgency.travelAgency.domain.hotel.repository.HotelRepository;
import com.travelAgency.travelAgency.domain.hotel.repository.ImagesRepository;
import com.travelAgency.travelAgency.domain.room.dto.response.RoomResponseDto;
import com.travelAgency.travelAgency.domain.room.entity.Rooms;
import com.travelAgency.travelAgency.domain.room.repository.RoomRepository;
import com.travelAgency.travelAgency.domain.room.service.RoomService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@SpringBootTest
class HotelServiceTest {

	@PersistenceContext
	EntityManager em;
	@Autowired
	private HotelRepository hotelRepository;
	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private ImagesRepository imagesRepository;

	@Autowired
	private RoomService roomService;
	@Autowired
	private HotelService hotelService;

	@Test
	@DisplayName("response all hotels")
	@WithMockUser
	public void responseALlHotels() {

		Hotels hotels1 = new Hotels(
			"test",
			"test1",
			"test",
			"test",
			"test"
		);

		Hotels hotels2 = new Hotels(
			"test",
			"test2",
			"test",
			"test",
			"test"
		);

		Hotels hotels3 = new Hotels(
			"test",
			"test3",
			"test",
			"test",
			"test"
		);

		HotelsImages hotelsImages1 = new HotelsImages(
			hotels1,
			"naver1.com"
		);

		HotelsImages hotelsImages2 = new HotelsImages(
			hotels1,
			"naver2.com"
		);

		HotelsImages hotelsImages3 = new HotelsImages(
			hotels1,
			"naver3.com"
		);

		Rooms rooms1 = new Rooms(
			"test1",
			"test1",
			"10000",
			"test1",
			"test1",
			true,
			true,
			true,
			true,
			true,
			hotels1
		);

		Rooms rooms11 = new Rooms(
			"test1",
			"test1",
			"100000",
			"test1",
			"test1",
			true,
			true,
			true,
			true,
			true,
			hotels1
		);

		Rooms rooms2 = new Rooms(
			"test1",
			"test1",
			"100000",
			"test1",
			"test1",
			true,
			true,
			true,
			true,
			true,
			hotels2
		);

		Rooms rooms22 = new Rooms(
			"test1",
			"test1",
			"1000",
			"test1",
			"test1",
			true,
			true,
			true,
			true,
			true,
			hotels2
		);

		Rooms rooms3 = new Rooms(
			"test1",
			"test1",
			"100",
			"test1",
			"test1",
			true,
			true,
			true,
			true,
			true,
			hotels3
		);

		Rooms rooms33 = new Rooms(
			"test1",
			"test1",
			"10",
			"test1",
			"test1",
			true,
			true,
			true,
			true,
			true,
			hotels3
		);

		hotelRepository.saveAll(List.of(hotels1, hotels2, hotels3));
		imagesRepository.saveAll(List.of(hotelsImages1, hotelsImages2, hotelsImages3));
		roomRepository.saveAll(List.of(rooms1, rooms2, rooms3, rooms11, rooms22, rooms33));

		ResponseEntity<List<HotelsResponseDto>> hotelsResponseDto = hotelService.getAllHotels(1, 1);

		Objects.requireNonNull(hotelsResponseDto.getBody()).forEach(
			e -> {
				System.out.println(e.getName());
				System.out.println(e.getLowestPrice());
				// System.out.println(e.getHotelsImagesList());
			}
		);

	}

	@Test
	@WithMockUser
	@DisplayName("get hotel details info")
	public void getHotelDetailsInfo(){

		Hotels hotels1 = new Hotels(
			"test",
			"test1",
			"test",
			"test",
			"test"
		);

		Hotels hotels2 = new Hotels(
			"test",
			"test2",
			"test",
			"test",
			"test"
		);

		Hotels hotels3 = new Hotels(
			"test",
			"test3",
			"test",
			"test",
			"test"
		);

		HotelsImages hotelsImages1 = new HotelsImages(
			hotels1,
			"naver1.com"
		);

		HotelsImages hotelsImages2 = new HotelsImages(
			hotels1,
			"naver2.com"
		);

		HotelsImages hotelsImages3 = new HotelsImages(
			hotels1,
			"naver3.com"
		);

		Rooms rooms1 = new Rooms(
			"test1",
			"test1",
			"10000",
			"test1",
			"test1",
			true,
			true,
			true,
			true,
			true,
			hotels1
		);

		Rooms rooms11 = new Rooms(
			"test11",
			"test1",
			"100000",
			"test1",
			"test1",
			true,
			true,
			true,
			true,
			true,
			hotels1
		);

		Rooms rooms2 = new Rooms(
			"test2",
			"test1",
			"100000",
			"test1",
			"test1",
			true,
			true,
			true,
			true,
			true,
			hotels1
		);

		Rooms rooms22 = new Rooms(
			"test22",
			"test1",
			"1000",
			"test1",
			"test1",
			true,
			true,
			true,
			true,
			true,
			hotels1
		);

		Rooms rooms3 = new Rooms(
			"test1",
			"test1",
			"100",
			"test1",
			"test1",
			true,
			true,
			true,
			true,
			true,
			hotels3
		);

		Rooms rooms33 = new Rooms(
			"test1",
			"test1",
			"10",
			"test1",
			"test1",
			true,
			true,
			true,
			true,
			true,
			hotels3
		);

		hotelRepository.saveAll(List.of(hotels1, hotels2, hotels3));
		imagesRepository.saveAll(List.of(hotelsImages1, hotelsImages2, hotelsImages3));
		roomRepository.saveAll(List.of(rooms1, rooms2, rooms3, rooms11, rooms22, rooms33));

		ResponseEntity<HotelsDetailsResponseDto> hotelServiceHotelDetailsInfo =hotelService.getHotelDetailsInfo(1L);

		System.out.println(hotelServiceHotelDetailsInfo.getBody());

		for(RoomResponseDto rooms : Objects.requireNonNull(hotelServiceHotelDetailsInfo.getBody()).getRoomsDtoList()){

			System.out.println(rooms.getName());
			System.out.println(rooms.getWifi());


		}




	}
}