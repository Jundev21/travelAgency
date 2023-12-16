package com.travelAgency.travelAgency.domain.room.entity;

import java.util.ArrayList;
import java.util.List;

import com.travelAgency.travelAgency.domain.hotel.entity.Hotels;
import com.travelAgency.travelAgency.domain.reservation.entity.Reservations;
import com.travelAgency.travelAgency.domain.stock.entity.Stocks;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Rooms {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private int travelers;
	private int price;
	private String bedNum;
	private String roomSize;
	private Boolean wifi;
	private Boolean tv;
	private Boolean shower;
	private Boolean food;
	private Boolean airCondition;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hotels_id")
	private Hotels hotels;
	@OneToMany(mappedBy = "rooms", cascade = CascadeType.ALL)
	private List<Stocks> stocksList = new ArrayList<>();
	@OneToMany(mappedBy = "rooms", cascade = CascadeType.ALL)
	private List<Reservations> reservationsList= new ArrayList<>();;

	public Rooms(
		String name,
		int travelers,
		int price,
		String bedNum,
		String roomsSize,
		Boolean wifi,
		Boolean tv,
		Boolean shower,
		Boolean food,
		Boolean airCondition
		){
		this.name = name;
		this.travelers = travelers;
		this.price = price;
		this.bedNum = bedNum;
		this.roomSize = roomsSize;
		this.wifi = wifi;
		this.tv = tv;
		this.shower = shower;
		this.food = food;
		this.airCondition = airCondition;
	}


	public void addHotels(Hotels hotels){
		this.hotels = hotels;
	}

	public void addStocks(Stocks stocks){
		stocks.addRoom(this);
		stocksList.add(stocks);

	}


}
