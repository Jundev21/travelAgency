package com.travelAgency.travelAgency.domain.wishList.entity;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.User;

import com.travelAgency.travelAgency.domain.hotel.entity.Hotels;
import com.travelAgency.travelAgency.domain.user.entity.Users;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;

@Entity
@Getter
public class WishLists {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Users users;
	@OneToMany( mappedBy = "wishLists")
	private List<Hotels> hotelsList = new ArrayList<>();




}
