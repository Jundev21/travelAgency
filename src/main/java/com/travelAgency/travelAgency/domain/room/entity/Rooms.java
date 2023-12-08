package com.travelAgency.travelAgency.domain.room.entity;

import com.travelAgency.travelAgency.domain.hotel.entity.Hotels;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Rooms {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String travelers;
	private String price;
	private String bedNum;
	private String roomSize;
	private Boolean wifi;
	private Boolean tv;
	private Boolean shower;
	private Boolean food;
	private Boolean airCondition;
	@ManyToOne(fetch = FetchType.LAZY)
	private Hotels hotels;

	public Rooms(
		String name,
		String travelers,
		String price,
		String bedNum,
		String roomsSize,
		Boolean wifi,
		Boolean tv,
		Boolean shower,
		Boolean food,
		Boolean airCondition,
		Hotels hotels
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
		this.hotels = hotels;

	}

}
