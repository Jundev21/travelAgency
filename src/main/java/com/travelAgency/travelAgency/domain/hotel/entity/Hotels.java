package com.travelAgency.travelAgency.domain.hotel.entity;

import java.util.List;

import com.travelAgency.travelAgency.domain.room.entity.Rooms;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	private Long id;
	private String address;
	private String name;
	private String stars;
	private String city;
	private String country;
	@OneToMany(mappedBy = "hotels")
	private List<Rooms> roomsList;
	@OneToMany(mappedBy = "hotels")
	private List<HotelsImages> hotelsImagesList;


	public Hotels(
		String address,
		String name,
		String stars,
		String city,
		String country
		){
		this.address = address;
		this.name = name;
		this.stars = stars;
		this.city = city;
		this.country = country;
	}


}
