package com.travelAgency.travelAgency.domain.room.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.travelAgency.travelAgency.domain.hotel.repository.HotelRepository;
import com.travelAgency.travelAgency.domain.room.entity.Rooms;
import com.travelAgency.travelAgency.domain.room.service.RoomService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RoomRepositoryQueryDSLImplTest {

	@PersistenceContext
	EntityManager em;
	@Autowired
	private HotelRepository hotelRepository;
	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private RoomService roomService;

	@Test
	public void testRoomPrice() {

		Rooms rooms = new Rooms(
			"test1",
			1,
			"1000",
			"test1",
			"test1",
			true,
			true,
			true,
			true,
			true
		);

		Rooms rooms1 = new Rooms(
			"test1",
			1,
			"10000",
			"test1",
			"test1",
			true,
			true,
			true,
			true,
			true
		);

		Rooms rooms2 = new Rooms(
			"test1",
			1,
			"100000",
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
			1,
			"100",
			"test1",
			"test1",
			true,
			true,
			true,
			true,
			true
		);

		roomRepository.saveAll(List.of(rooms, rooms1, rooms2));

	}

}