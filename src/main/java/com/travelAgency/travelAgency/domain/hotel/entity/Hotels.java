package com.travelAgency.travelAgency.domain.hotel.entity;

import java.util.ArrayList;
import java.util.List;

import com.travelAgency.travelAgency.domain.room.entity.Rooms;
import com.travelAgency.travelAgency.domain.wishList.entity.WishLists;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Hotels {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String address;
	private String name;
	private String stars;
	private String city;
	private String country;
	@OneToMany(mappedBy = "hotels")
	private List<Rooms> roomsList = new ArrayList<>();
	@OneToMany(mappedBy = "hotels")
	private List<HotelsImages> hotelsImagesList= new ArrayList<>();
	@ManyToOne(fetch = FetchType.LAZY)
	private WishLists wishLists;


	public Hotels(
		String address,
		String name,
		String stars,
		String city,
		String country,
		WishLists wishLists
		){
		this.address = address;
		this.name = name;
		this.stars = stars;
		this.city = city;
		this.country = country;
		this.wishLists = wishLists;
	}


}
