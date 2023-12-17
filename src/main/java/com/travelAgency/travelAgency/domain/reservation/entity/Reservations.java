package com.travelAgency.travelAgency.domain.reservation.entity;

import java.time.LocalDate;

import com.travelAgency.travelAgency.domain.payment.entity.Payments;
import com.travelAgency.travelAgency.domain.room.entity.Rooms;
import com.travelAgency.travelAgency.domain.user.entity.Users;

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

public class Reservations {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate checkIn;
	private LocalDate checkOut;
	private String name;
	private String phoneNumber;
	private String email;
	private String payStatus;
	private String reservationNumber;
	private String travelers;
	@ManyToOne(fetch = FetchType.LAZY)
	private Users users;
	@ManyToOne(fetch = FetchType.LAZY)
	private Rooms rooms;
	@ManyToOne(fetch = FetchType.LAZY)
	private Payments payments;

	public Reservations(
		String travelers,
		LocalDate checkIn,
		LocalDate checkOut,
		String name,
		String phoneNumber,
		String email,
		String payStatus,
		String reservationNumber,
		Users users,
		Rooms rooms
	) {
		this.travelers = travelers;
		this.name =name;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.payStatus = payStatus;
		this.reservationNumber = reservationNumber;
		this.users = users;
		this.rooms = rooms;
	}

}
