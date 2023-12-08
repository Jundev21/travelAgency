package com.travelAgency.travelAgency.domain.room.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.travelAgency.travelAgency.domain.room.entity.QRooms;
import com.travelAgency.travelAgency.domain.room.entity.Rooms;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RoomRepositoryQueryDSLImpl implements RoomRepositoryQueryDSL {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public String getLowestPrice(Long hotelId) {

		QRooms rooms = QRooms.rooms;

		return jpaQueryFactory
			.select(rooms.price.min())
			.from(rooms)
			.where(rooms.hotels.id.eq(hotelId))
			.fetchFirst();


	}
}
