package com.travelAgency.travelAgency.domain.payment.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.travelAgency.travelAgency.domain.reservation.entity.Reservations;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Payments {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String price; // 결제 금액
	private String paymentMethod;
	@CreatedDate
	private LocalDateTime paymentDate;
	@OneToMany(mappedBy = "payments", cascade = CascadeType.ALL)
	private List<Reservations> reservationsList;

	public void addReservationList(Reservations reservations){
		this.reservationsList.add(reservations);
		reservations.setReservation(this);
	}

}
