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
public class RoomsImages {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	private Rooms rooms;
	private String imageUrl;

	public RoomsImages(
		Rooms rooms,
		String imageUrl
	) {
		this.rooms = rooms;
		this.imageUrl = imageUrl;
	}
}
