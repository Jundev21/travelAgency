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
			2,
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
			2,
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
			2,
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
			2,
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
			2,
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
			2,
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
			2,
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
			2,
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
			2,
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
			2,
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

		ResponseEntity<HotelsDetailsResponseDto> hotelServiceHotelDetailsInfo = hotelService.getHotelDetailsInfo(1L);

		System.out.println(hotelServiceHotelDetailsInfo.getBody());

		for (RoomResponseDto rooms : Objects.requireNonNull(hotelServiceHotelDetailsInfo.getBody()).getRoomsDtoList()) {

			System.out.println(rooms.getName());
			System.out.println(rooms.getWifi());

		}
	}

	@Test
	@WithMockUser
	@DisplayName("get hotel details info")
	public void getFilterHotelData() {

		Hotels hotels = new Hotels(
			"test",
			"test1",
			"test",
			"test",
			"test"
		);

		Rooms rooms1 = new Rooms(
			"test1",
			2,
			"10000",
			"test1",
			"test1",
			true,
			true,
			true,
			true,
			true,
			hotels
		);

		Rooms rooms2 = new Rooms(
			"test2",
			4,
			"100000",
			"test1",
			"test1",
			true,
			true,
			true,
			true,
			true,
			hotels
		);

		Rooms rooms3 = new Rooms(
			"test3",
			2,
			"100",
			"test1",
			"test1",
			true,
			true,
			true,
			true,
			true,
			hotels
		);

		Rooms rooms4 = new Rooms(
			"test4",
			2,
			"10",
			"test1",
			"test1",
			true,
			true,
			true,
			true,
			true,
			hotels
		);

		Rooms rooms5 = new Rooms(
			"test5",
			2,
			"10",
			"test1",
			"test1",
			true,
			true,
			true,
			true,
			true,
			hotels
		);

		Rooms rooms6 = new Rooms(
			"test6",
			4,
			"10",
			"test1",
			"test1",
			true,
			true,
			true,
			true,
			true,
			hotels
		);

		Rooms rooms7 = new Rooms(
			"test7",
			4,
			"1",
			"test1",
			"test1",
			true,
			true,
			true,
			true,
			true,
			hotels
		);

		Stocks stocks1 = new Stocks(LocalDate.of(2023, 12, 1), 4, rooms1);
		Stocks stocks12 = new Stocks(LocalDate.of(2023, 12, 2), 3, rooms1);
		Stocks stocks13 = new Stocks(LocalDate.of(2023, 12, 3), 4, rooms1);
		Stocks stocks14 = new Stocks(LocalDate.of(2023, 12, 4), 0, rooms1);
		Stocks stocks15 = new Stocks(LocalDate.of(2023, 12, 5), 4, rooms1);

		Stocks stocks21 = new Stocks(LocalDate.of(2023, 12, 1), 0, rooms2);
		Stocks stocks22 = new Stocks(LocalDate.of(2023, 12, 2), 0, rooms2);
		Stocks stocks23 = new Stocks(LocalDate.of(2023, 12, 3), 0, rooms2);
		Stocks stocks24 = new Stocks(LocalDate.of(2023, 12, 4), 4, rooms2);
		Stocks stocks25 = new Stocks(LocalDate.of(2023, 12, 5), 0, rooms2);

		Stocks stocks31 = new Stocks(LocalDate.of(2023, 12, 1), 4, rooms3);
		Stocks stocks32 = new Stocks(LocalDate.of(2023, 12, 2), 0, rooms3);
		Stocks stocks33 = new Stocks(LocalDate.of(2023, 12, 3), 0, rooms3);
		Stocks stocks34 = new Stocks(LocalDate.of(2023, 12, 4), 0, rooms3);
		Stocks stocks35 = new Stocks(LocalDate.of(2023, 12, 5), 4, rooms3);

		Stocks stocks41 = new Stocks(LocalDate.of(2023, 12, 1), 0, rooms4);
		Stocks stocks42 = new Stocks(LocalDate.of(2023, 12, 2), 0, rooms4);
		Stocks stocks43 = new Stocks(LocalDate.of(2023, 12, 3), 4, rooms4);
		Stocks stocks44 = new Stocks(LocalDate.of(2023, 12, 4), 4, rooms4);
		Stocks stocks45 = new Stocks(LocalDate.of(2023, 12, 5), 4, rooms4);

		Stocks stocks51 = new Stocks(LocalDate.of(2023, 12, 1), 4, rooms5);
		Stocks stocks52 = new Stocks(LocalDate.of(2023, 12, 2), 4, rooms5);
		Stocks stocks53 = new Stocks(LocalDate.of(2023, 12, 3), 0, rooms5);
		Stocks stocks54 = new Stocks(LocalDate.of(2023, 12, 4), 4, rooms5);
		Stocks stocks55 = new Stocks(LocalDate.of(2023, 12, 5), 4, rooms5);

		Stocks stocks61 = new Stocks(LocalDate.of(2023, 12, 1), 4, rooms6);
		Stocks stocks62 = new Stocks(LocalDate.of(2023, 12, 2), 4, rooms6);
		Stocks stocks63 = new Stocks(LocalDate.of(2023, 12, 3), 4, rooms6);
		Stocks stocks64 = new Stocks(LocalDate.of(2023, 12, 4), 4, rooms6);
		Stocks stocks65 = new Stocks(LocalDate.of(2023, 12, 5), 4, rooms6);

		Stocks stocks71 = new Stocks(LocalDate.of(2023, 12, 1), 4, rooms7);
		Stocks stocks72 = new Stocks(LocalDate.of(2023, 12, 2), 4, rooms7);
		Stocks stocks73 = new Stocks(LocalDate.of(2023, 12, 3), 4, rooms7);
		Stocks stocks74 = new Stocks(LocalDate.of(2023, 12, 4), 4, rooms7);
		Stocks stocks75 = new Stocks(LocalDate.of(2023, 12, 5), 4, rooms7);

		// 재고 부분에서 - (날짜 2023,12,1 / 2023,12,2 / 2023,12,3)
		// 1,2,6,7 번 방 통과
		// 여행자 필터에서 - (4명)
		// 2, 7 번방 통과

		hotelRepository.save(hotels);
		roomRepository.saveAll(List.of(rooms1, rooms2, rooms3, rooms4, rooms5, rooms6, rooms7));
		stockRepository.saveAll(
			List.of(
				stocks1, stocks12, stocks13, stocks14, stocks15,
				stocks21, stocks22, stocks23, stocks24, stocks25,
				stocks31, stocks32, stocks33, stocks34, stocks35,
				stocks41, stocks42, stocks43, stocks44, stocks45,
				stocks51, stocks52, stocks53, stocks54, stocks55,
				stocks61, stocks62, stocks63, stocks64, stocks65,
				stocks71, stocks72, stocks73, stocks74, stocks75
			)
		);


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



		ROOM TEST

		6,7 번방이 나와야함.
		List<Rooms> rooms = hotelRepository.getAvailableRooms(
			LocalDate.of(2023, 12, 1),
			LocalDate.of(2023, 12, 4),
			4
		);

		for (Rooms roomsTest : rooms) {

			System.out.println("================");

			System.out.println("room name");
			System.out.println(roomsTest.getName());

			System.out.println("roomPrice");
			System.out.println(roomsTest.getPrice());

			System.out.println("ID");
			System.out.println(roomsTest.getId());

		}


		//ROOM TEST

		// 6,7 번방이 나와야함.
		// List<Rooms> rooms = hotelRepository.getAvailableRooms(
		// 	LocalDate.of(2023, 12, 1),
		// 	LocalDate.of(2023, 12, 4),
		// 	4
		// );
		//
		// for (Rooms roomsTest : rooms) {
		//
		// 	System.out.println("================");
		//
		// 	System.out.println("room name");
		// 	System.out.println(roomsTest.getName());
		//
		// 	System.out.println("roomPrice");
		// 	System.out.println(roomsTest.getPrice());
		//
		// 	System.out.println("ID");
		// 	System.out.println(roomsTest.getId());
		//
		// }


		// Stocks TEST
		// List<Stocks> stocks = hotelRepository.getStocksFilter(
		// 	LocalDate.of(2023, 12, 1),
		// 	LocalDate.of(2023, 12, 4)
		// );
		//
		// for(Stocks stocksTest : stocks){
		//
		// 	System.out.println("===========================");
		// 	System.out.println("room ID " + stocksTest.getRooms().getId());
		// 	System.out.println("stock id  " + stocksTest.getDate());
		//
		// }
	}

}
