package com.travelAgency.travelAgency.domain.reservation.controller;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelAgency.travelAgency.domain.reservation.dto.request.ReservationRequest;
import com.travelAgency.travelAgency.domain.reservation.dto.response.ReservationToEntity;
import com.travelAgency.travelAgency.domain.reservation.service.ReservationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ReservationController {

	private final ReservationService reservationService;

	@GetMapping("/reservation/{reservationId}")
	public ResponseEntity<ReservationToEntity> getReservationInfo(
		@PathVariable long reservationID
	){
		return reservationService.getReservationInfo(reservationID);
	}

	@PostMapping("/hotel/{hotelId}/room/{roomId}/reservation")
	public ResponseEntity<String> enrollReservation(
		@PathVariable long hotelId,
		@PathVariable long roomId,
		@RequestBody ReservationRequest reservationRequest,
		Principal principal
	){
		return reservationService.enrollReservation(principal ,hotelId, roomId, reservationRequest);
	}
}
