package com.travelAgency.travelAgency.domain.room.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.travelAgency.travelAgency.domain.room.entity.QRooms;
import com.travelAgency.travelAgency.domain.room.entity.Rooms;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RoomRepositoryQueryDSLImpl implements RoomRepositoryQueryDSL {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public int getLowestPrice(Long hotelId) {
		QRooms room = QRooms.rooms;
		return jpaQueryFactory
			// .select(room.price.min()) // 최저 가격을 조회하도록 수정 (max() -> min())
			.select(room.price.min())
			.from(room)
			.where(room.hotels.id.eq(hotelId))
			.fetchFirst();

	}
}
