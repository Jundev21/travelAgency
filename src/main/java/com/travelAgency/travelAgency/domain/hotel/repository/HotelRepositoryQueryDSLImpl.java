package com.travelAgency.travelAgency.domain.hotel.repository;

import static org.springframework.boot.origin.Origin.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.travelAgency.travelAgency.domain.hotel.entity.Hotels;
import com.travelAgency.travelAgency.domain.hotel.entity.QHotels;
import com.travelAgency.travelAgency.domain.room.entity.QRooms;
import com.travelAgency.travelAgency.domain.room.entity.Rooms;
import com.travelAgency.travelAgency.domain.stock.entity.QStocks;
import com.travelAgency.travelAgency.domain.stock.entity.Stocks;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class HotelRepositoryQueryDSLImpl implements HotelRepositoryQueryDSL{

	private final JPAQueryFactory jpaQueryFactory;

	// @Override
	// public List<Hotels> getCheckInCheckOutAddressCityTravelers(LocalDate checkIn, LocalDate checkOut, String city,
	// 	String address, int travelers) {
	//
	// 	QRooms rooms = QRooms.rooms;
	// 	QHotels hotels = QHotels.hotels;
	//
	// 	// sql 문으로 먼저 작성해보기
	// 	// 조건이 5개가 있다.
	// 	// where 절로 조건 걸기
	// 	// 1. between 을 사용하여 check in 과 check out 을 확인하기.
	// 	// 2. equal 을 사용하여 city 확인
	// 	// 3. equal 을 사용하여 address 확인
	// 	// 4. 크거나 같을때, 즉 >= 을 사용하여 travelers 확인
	//
	// 	// select * from hotels
	// 	// where hotels.city = city
	// 	// AND hotels.address = address
	// 	// AND rooms.travelers >= travelers
	// 	// AND stocks.date between checkin AND checkout
	//
	// 	// fetch() :  리스트로 결과를 반환하는 방법
	// 	// fetchOne() : 단건을 조회할 때 사용하는 방법
	// 	// fetchFirst() : 처음의 한건을 쿼리해서 가져오고 싶을때 사용
	// 	// fetchResults() : 해당 내용은 페이징을 위해 사용될 수 있습니다. 페이징을 위해서 total contents를 가져옴
	// 	// 	fetchCount() : count 쿼리
	//
	// 	return jpaQueryFactory
	// 		.selectFrom(hotels)
	// 		.where(
	// 			hotels.id.in(
	// 				JPAExpressions
	// 					.select(rooms.hotels.id)
	// 					.from(rooms)
	// 					.where(rooms.in(getAvailableRooms(checkIn, checkOut, travelers,city,address)))
	// 			)
	// 		)
	// 		.fetch();
	// }

	@Override
	public List<Hotels> getCheckInCheckOutAddressCityTravelers(
		LocalDate checkIn, LocalDate checkOut, String city,
		String address, int travelers, long pageOffset, int pageSize
	) {

		QRooms rooms = QRooms.rooms;
		QHotels hotels = QHotels.hotels;

		return jpaQueryFactory
			.selectFrom(hotels)
			.where(
				hotels.id.in(
						JPAExpressions
							.select(rooms.hotels.id)
							.from(rooms)
							.where(rooms.in(getAvailableRooms(checkIn, checkOut, travelers)))
					)
					.and(hotels.city.eq(city))
					.and(hotels.address.eq(address))
			)
			.fetch();
		// return jpaQueryFactory
		// 	.selectFrom(hotels)
		// 	.where(
		// 		hotels.id.in(
		// 			JPAExpressions
		// 				.select(rooms.hotels.id)
		// 				.from(rooms)
		// 				.where(rooms.in(getAvailableRooms(checkIn, checkOut, travelers, city, address)))
		// 		)
		// 	)
		// 	.offset(pageOffset)
		// 	.limit(pageSize)
		// 	.fetch();
	}


	//pagenation
	@Override
	public List<Rooms> getAvailableRooms(LocalDate checkIn, LocalDate checkOut, int travelers) {
		QHotels hotels = QHotels.hotels;
		QRooms rooms = QRooms.rooms;
		QStocks stocks = QStocks.stocks;

		return jpaQueryFactory
			.selectFrom(rooms)
			.where(
				rooms.id.in(
					JPAExpressions
						.select(stocks.rooms.id)
						.from(stocks)
						.where(
							stocks.date.between(checkIn,checkOut)
								.and(stocks.roomStocks.gt(0)) // 재고가 1보다 큰 방 필터링
								.and(rooms.travelers.goe(travelers))
						)
						.groupBy(stocks.rooms.id)
						.having(stocks.rooms.id.count().eq(ChronoUnit.DAYS.between(checkIn, checkOut) + 1))
				)
			)
			.fetch();
		// return jpaQueryFactory
		// 	.selectFrom(rooms)
		// 	.where(
		// 		rooms.id.in(
		// 			JPAExpressions
		// 				.select(stocks.rooms.id)
		// 				.from(stocks)
		// 				.where(
		// 					stocks.date.between(checkIn,checkOut)
		// 						.and(stocks.roomStocks.gt(0)) // 재고가 0보다 큰 방 필터링
		// 				)
		// 				.groupBy(stocks.rooms.id)
		// 				.having(stocks.rooms.id.count().eq(ChronoUnit.DAYS.between(checkIn, checkOut) + 1))
		// 		)
		// 	)
		// 	.fetch();
	}

	@Override
	public List<Stocks> getStocksFilter(LocalDate checkIn, LocalDate checkOut) {

		QStocks stocks = QStocks.stocks;
		QRooms rooms = QRooms.rooms;

		return jpaQueryFactory
			.select(stocks)
			.from(rooms,stocks)
			.where(
				stocks.date.between(checkIn, checkOut)
			)
			.having(
				stocks.roomStocks.gt(0)
			)
			.fetch();
	}


}

