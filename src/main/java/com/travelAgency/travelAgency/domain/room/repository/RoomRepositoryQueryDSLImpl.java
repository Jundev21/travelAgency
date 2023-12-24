package com.travelAgency.travelAgency.domain.room.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.travelAgency.travelAgency.domain.room.entity.QRooms;
import com.travelAgency.travelAgency.domain.room.entity.Rooms;
import com.travelAgency.travelAgency.domain.stock.entity.QStocks;

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

	@Override
	public Long checkAvailableRooms(LocalDate checkIn, LocalDate checkOut, int travelers, long targetRoomId) {
		QRooms rooms = QRooms.rooms;
		QStocks stocks = QStocks.stocks;

		return jpaQueryFactory
			.select(rooms.id.count())
			.from(rooms)
			.where(
				rooms.id.in(
						JPAExpressions
							.select(stocks.rooms.id)
							.from(stocks)
							.where(
								stocks.date.between(checkIn, checkOut)
									.and(stocks.roomStocks.gt(0)) // 재고가 1보다 큰 방 필터링
									.and(rooms.travelers.goe(travelers))
							)
							.groupBy(stocks.rooms.id)
							.having(stocks.rooms.id.count().eq(ChronoUnit.DAYS.between(checkIn, checkOut) + 1))
					)
					.and(rooms.id.eq(targetRoomId))
			)
			.fetchFirst();
	}

}
