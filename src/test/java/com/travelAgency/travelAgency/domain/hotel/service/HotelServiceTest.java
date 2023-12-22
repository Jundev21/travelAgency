package com.travelAgency.travelAgency.domain.hotel.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.BeforeEach;
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
import com.travelAgency.travelAgency.domain.stock.entity.Stocks;
import com.travelAgency.travelAgency.domain.stock.repository.StockRepository;
import com.travelAgency.travelAgency.domain.user.entity.Role;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
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
	private StockRepository stockRepository;

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
			"hotel1",
			"test",
			"test",
			"test"
		);


		Rooms rooms1 = new Rooms(
			"test1",
			2,
			10000,
			"test1",
			"test1",
			true,
			true,
			true,
			true,
			true
		);


		hotels1.addRoomList(rooms1);
		Hotels hotelss = hotelRepository.save(hotels1);


		List<Rooms> rooms = roomRepository.findAll();
		System.out.println(rooms.get(0).getHotels().getName());

	}

	@Test
	@WithMockUser
	@DisplayName("get hotel details info")
	public void getHotelDetailsInfo() {

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
			2,
			10000,
			"test1",
			"test1",
			true,
			true,
			true,
			true,
			true
			// hotels1
		);

		Rooms rooms11 = new Rooms(
			"test11",
			2,
			100000,
			"test1",
			"test1",
			true,
			true,
			true,
			true,
			true
			// hotels1
		);

		Rooms rooms2 = new Rooms(
			"test2",
			2,
			100000,
			"test1",
			"test1",
			true,
			true,
			true,
			true,
			true
			// hotels1
		);

		Rooms rooms22 = new Rooms(
			"test22",
			2,
			1000,
			"test1",
			"test1",
			true,
			true,
			true,
			true,
			true

		);

		Rooms rooms3 = new Rooms(
			"test1",
			2,
			100,
			"test1",
			"test1",
			true,
			true,
			true,
			true,
			true

		);

		Rooms rooms33 = new Rooms(
			"test1",
			2,
			10,
			"test1",
			"test1",
			true,
			true,
			true,
			true,
			true
		);

		hotelRepository.saveAll(List.of(hotels1, hotels2, hotels3));
		imagesRepository.saveAll(List.of(hotelsImages1, hotelsImages2, hotelsImages3));
		roomRepository.saveAll(List.of(rooms1, rooms2, rooms3, rooms11, rooms22, rooms33));

		ResponseEntity<HotelsDetailsResponseDto> hotelServiceHotelDetailsInfo = hotelService.getHotelDetailsInfo(1L);
		System.out.println(hotelServiceHotelDetailsInfo.getBody());

		for (RoomResponseDto rooms : Objects.requireNonNull(hotelServiceHotelDetailsInfo.getBody()).getRoomsDtoList()) {
			System.out.println(rooms.getName());
			System.out.println(rooms.getWifi());
		}
	}

	@Test
	@WithMockUser()
	@DisplayName("get hotel details info")
	public void getFilterHotelData() {

		//given
		mockData();
		// 재고 부분에서 - (날짜 2023,12,1 / 2023,12,2 / 2023,12,3)
		// 1,2,6,7 번 방 통과
		// 여행자 필터에서 - (4명)
		// 2, 7 번방 통과

		// stockRepository.saveAll(
		// 	List.of(
		// 		stocks1, stocks12, stocks13, stocks14, stocks15,
		// 		stocks21, stocks22, stocks23, stocks24, stocks25,
		// 		stocks31, stocks32, stocks33, stocks34, stocks35
		// 		// stocks41, stocks42, stocks43, stocks44, stocks45,
		// 		// stocks51, stocks52, stocks53, stocks54, stocks55,
		// 		// stocks61, stocks62, stocks63, stocks64, stocks65,
		// 		// stocks71, stocks72, stocks73, stocks74, stocks75
		// 	)
		// );


		//HOTEL TEST

		// ResponseEntity<List<HotelsResponseDto>> hotelFiltering =hotelService.getFilterHotels(
		// 1,
		// 	1,
		// 	LocalDate.of(2023,12,1),
		// 	LocalDate.of(2023,12,4),
		// 	"test",
		// 	"test",
		// 	4
		// );
		//
		// for(HotelsResponseDto r: Objects.requireNonNull(hotelFiltering.getBody())){
		// 	System.out.println("========================");
		// 	System.out.println(r.getName());
		// 	System.out.println(r.getCity());
		// 	System.out.println(r.getAddress());
		// 	System.out.println(r.getLowestPrice());
		// 	System.out.println(r.getStars());
		// }



		// HOTEL TEST
		//
		// 6,7 번방이 나와야함.
		List<Hotels> hotelsList = hotelRepository.getCheckInCheckOutAddressCityTravelers(
			LocalDate.of(2023, 12, 1),
			LocalDate.of(2023, 12, 4),
			"test",
			"test",
			4,
			0,
			0
		);

		for (Hotels hotelTest : hotelsList) {
			assertEquals(hotelTest.getRoomsList().get(0).getName(),"test6");
			assertEquals(hotelTest.getRoomsList().get(1).getName(),"test7");
		}

	}

	@Test
	@WithMockUser
	@DisplayName("get hotel details info")
	public void getRoomsDetailsInfo() {

		mockData();
		//ROOM TEST
		// 6,7 번방이 나와야함.
		List<Rooms> rooms = hotelRepository.getAvailableRooms(
			LocalDate.of(2023, 12, 1),
			LocalDate.of(2023, 12, 4),
			4
			// "Gang Nam",
			// "Soul"
		);
		assertEquals(rooms.get(0).getName(),"test6");
		assertEquals(rooms.get(1).getName(),"test7");
	}

	@Test
	@WithMockUser
	@DisplayName("get stocks details info")
	public void getStocksDetailsInfo() {

		mockData();
		// Stocks TEST
		// 6,7 room
		List<Stocks> stocks = hotelRepository.getStocksFilter(
			LocalDate.of(2023, 12, 1),
			LocalDate.of(2023, 12, 4)
		);

		assertEquals(stocks.get(0).getRooms().getName(), "test6");
		assertEquals(stocks.get(0).getRooms().getName(), "test7");
	}

	public void mockData(){

		Hotels hotels = new Hotels(
			"test",
			"hotel1",
			"test",
			"test",
			"test"
		);

		Hotels hotels2 = new Hotels(
			"test",
			"hotel2",
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


		Rooms rooms1 = new Rooms(
			"test1",
			4,
			10000,
			"test1",
			"test1",
			true,
			true,
			true,
			true,
			true
		);

		Rooms rooms2 = new Rooms(
			"test2",
			4,
			100000,
			"test1",
			"test1",
			true,
			true,
			true,
			true,
			true
		);

		Rooms rooms3 = new Rooms(
			"test3",
			2,
			100,
			"test1",
			"test1",
			true,
			true,
			true,
			true,
			true
		);

		Rooms rooms4 = new Rooms(
			"test4",
			2,
			10,
			"test1",
			"test1",
			true,
			true,
			true,
			true,
			true
		);

		Rooms rooms5 = new Rooms(
			"test5",
			2,
			10,
			"test1",
			"test1",
			true,
			true,
			true,
			true,
			true
		);

		Rooms rooms6 = new Rooms(
			"test6",
			4,
			10,
			"test1",
			"test1",
			true,
			true,
			true,
			true,
			true
		);

		Rooms rooms7 = new Rooms(
			"test7",
			4,
			1,
			"test1",
			"test1",
			true,
			true,
			true,
			true,
			true
		);

		Stocks stocks1 = new Stocks(LocalDate.of(2023, 12, 1), 4);
		Stocks stocks12 = new Stocks(LocalDate.of(2023, 12, 2), 3);
		Stocks stocks13 = new Stocks(LocalDate.of(2023, 12, 3), 4);
		Stocks stocks14 = new Stocks(LocalDate.of(2023, 12, 4), 4);
		Stocks stocks15 = new Stocks(LocalDate.of(2023, 12, 5), 4);

		Stocks stocks21 = new Stocks(LocalDate.of(2023, 12, 1), 2);
		Stocks stocks22 = new Stocks(LocalDate.of(2023, 12, 2), 2);
		Stocks stocks23 = new Stocks(LocalDate.of(2023, 12, 3), 2);
		Stocks stocks24 = new Stocks(LocalDate.of(2023, 12, 4), 4);
		Stocks stocks25 = new Stocks(LocalDate.of(2023, 12, 5), 0);

		Stocks stocks31 = new Stocks(LocalDate.of(2023, 12, 1), 4);
		Stocks stocks32 = new Stocks(LocalDate.of(2023, 12, 2), 0);
		Stocks stocks33 = new Stocks(LocalDate.of(2023, 12, 3), 0);
		Stocks stocks34 = new Stocks(LocalDate.of(2023, 12, 4), 0);
		Stocks stocks35 = new Stocks(LocalDate.of(2023, 12, 5), 4);


		rooms1.addStocks(stocks1);
		rooms1.addStocks(stocks12);
		rooms1.addStocks(stocks13);
		rooms1.addStocks(stocks14);
		rooms1.addStocks(stocks15);

		rooms2.addStocks(stocks21);
		rooms2.addStocks(stocks22);
		rooms2.addStocks(stocks23);
		rooms2.addStocks(stocks24);
		rooms2.addStocks(stocks25);

		rooms3.addStocks(stocks31);
		rooms3.addStocks(stocks32);
		rooms3.addStocks(stocks33);
		rooms3.addStocks(stocks34);
		rooms3.addStocks(stocks35);

		hotels.addRoomList(rooms1);
		hotels2.addRoomList(rooms2);
		hotels3.addRoomList(rooms3);
		hotels.addRoomList(rooms4);
		hotels.addRoomList(rooms5);


		hotelRepository.saveAll(List.of(hotels, hotels2, hotels3));

		Stocks stocks41 = new Stocks(LocalDate.of(2023, 12, 1), 0);
		Stocks stocks42 = new Stocks(LocalDate.of(2023, 12, 2), 0);
		Stocks stocks43 = new Stocks(LocalDate.of(2023, 12, 3), 4);
		Stocks stocks44 = new Stocks(LocalDate.of(2023, 12, 4), 4);
		Stocks stocks45 = new Stocks(LocalDate.of(2023, 12, 5), 4);

		Stocks stocks51 = new Stocks(LocalDate.of(2023, 12, 1), 4);
		Stocks stocks52 = new Stocks(LocalDate.of(2023, 12, 2), 4);
		Stocks stocks53 = new Stocks(LocalDate.of(2023, 12, 3), 0);
		Stocks stocks54 = new Stocks(LocalDate.of(2023, 12, 4), 4);
		Stocks stocks55 = new Stocks(LocalDate.of(2023, 12, 5), 4);

		Stocks stocks61 = new Stocks(LocalDate.of(2023, 12, 1), 4);
		Stocks stocks62 = new Stocks(LocalDate.of(2023, 12, 2), 4);
		Stocks stocks63 = new Stocks(LocalDate.of(2023, 12, 3), 4);
		Stocks stocks64 = new Stocks(LocalDate.of(2023, 12, 4), 4);
		Stocks stocks65 = new Stocks(LocalDate.of(2023, 12, 5), 4);

		Stocks stocks71 = new Stocks(LocalDate.of(2023, 12, 1), 4);
		Stocks stocks72 = new Stocks(LocalDate.of(2023, 12, 2), 4);
		Stocks stocks73 = new Stocks(LocalDate.of(2023, 12, 3), 4);
		Stocks stocks74 = new Stocks(LocalDate.of(2023, 12, 4), 4);
		Stocks stocks75 = new Stocks(LocalDate.of(2023, 12, 5), 4);

	}

}
