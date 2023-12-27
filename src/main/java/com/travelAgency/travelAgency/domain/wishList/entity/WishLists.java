package com.travelAgency.travelAgency.domain.wishList.entity;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.User;

import com.travelAgency.travelAgency.domain.hotel.entity.Hotels;
import com.travelAgency.travelAgency.domain.user.entity.Users;

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

public class WishLists {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	private Users users;
	@ManyToOne(fetch = FetchType.LAZY)
	private Hotels hotels;


	public WishLists(
		Users users,
		Hotels hotels
	){
		this.users = users;
		this.hotels = hotels;
	}
}
