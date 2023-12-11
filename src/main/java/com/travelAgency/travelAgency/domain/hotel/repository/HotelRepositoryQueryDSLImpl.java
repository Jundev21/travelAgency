package com.travelAgency.travelAgency.domain.hotel.repository;

import static org.springframework.boot.origin.Origin.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPAExpressions;
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

	@Override
	public List<Hotels> getCheckInCheckOutAddressCityTravelers(LocalDate checkIn, LocalDate checkOut, String city,
		String address, int travelers) {

		QRooms rooms = QRooms.rooms;
		QHotels hotels = QHotels.hotels;
		QStocks stocks = QStocks.stocks;

		// sql 문으로 먼저 작성해보기
		// 조건이 5개가 있다.
		// where 절로 조건 걸기
		// 1. between 을 사용하여 check in 과 check out 을 확인하기.
		// 2. equal 을 사용하여 city 확인
		// 3. equal 을 사용하여 address 확인
		// 4. 크거나 같을때, 즉 >= 을 사용하여 travelers 확인

		// select * from hotels
		// where hotels.city = city
		// AND hotels.address = address
		// AND rooms.travelers >= travelers
		// AND stocks.date between checkin AND checkout


		// select h1_0.id,h1_0.address,h1_0.city,h1_0.country,h1_0.name,h1_0.stars
		// from hotels h1_0
		// join rooms r1_0 on h1_0.id=r1_0.hotels_id
		// join stocks s1_0 on r1_0.id=s1_0.rooms_id
		// where h1_0.city=?
		// and h1_0.address=?
		// and r1_0.travelers>=?
		// and s1_0.room_stocks>?
		// and s1_0.date between ? and ?

		// fetch() :  리스트로 결과를 반환하는 방법입니다. (만약에 데이터가 없으면 빈 리스트를 반환해줍니다.)
		// fetchOne() : 단건을 조회할 때 사용하는 방법인데요. (결과가 없을때는 null 을 반환하고 결과가 둘 이상일 경우에는 NonUniqueResultException을 던집니다.)
		// fetchFirst() : 처음의 한건을 쿼리해서 가져오고 싶을때 사용하고요.
		// fetchResults() : 해당 내용은 페이징을 위해 사용될 수 있습니다. 페이징을 위해서 total contents를 가져오고요.
		// 	fetchCount() : count 쿼리를 날릴 수 있다.

		return jpaQueryFactory
			.selectFrom(hotels)
			.where(
				hotels.id.in(
					JPAExpressions
						.select(rooms.hotels.id)
						.from(rooms)
						.where(rooms.in(getAvailableRooms(checkIn, checkOut, travelers)))
				)
			)
			.fetch();
	}


	// Hibernate:
	// select r1_0.id,r1_0.air_condition,r1_0.bed_num,r1_0.food,r1_0.hotels_id,r1_0.name,r1_0.price,r1_0.room_size,r1_0.shower,r1_0.travelers,r1_0.tv,r1_0.wifi from rooms r1_0
	// where r1_0.id
	// in(
	// 		select s1_0.rooms_id
	// 		from stocks s1_0
	// 		where s1_0.date between ? and ?
	// 		and s1_0.room_stocks>?
	// 		and r1_0.travelers>=?
	// 		group by s1_0.rooms_id
	// 		having count(s1_0.rooms_id)=?
	// 		)

	// Hibernate:
	// select h1_0.id,h1_0.address,h1_0.city,h1_0.country,h1_0.name,h1_0.stars
	// from hotels h1_0
	// where h1_0.id
	// in(select r1_0.hotels_id from rooms r1_0 where r1_0.id in (?,?))


	@Override
	public List<Rooms> getAvailableRooms(LocalDate checkIn, LocalDate checkOut, int travelers) {

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

