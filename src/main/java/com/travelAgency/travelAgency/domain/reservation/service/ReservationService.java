package com.travelAgency.travelAgency.domain.reservation.service;

import java.security.Principal;
import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.travelAgency.travelAgency.domain.common.error.ErrorCode;
import com.travelAgency.travelAgency.domain.common.exception.NormalException;
import com.travelAgency.travelAgency.domain.hotel.entity.Hotels;
import com.travelAgency.travelAgency.domain.hotel.repository.HotelRepository;
import com.travelAgency.travelAgency.domain.reservation.dto.ReservationMapper;
import com.travelAgency.travelAgency.domain.reservation.dto.request.ReservationRequest;
import com.travelAgency.travelAgency.domain.reservation.dto.response.ReservationToEntity;
import com.travelAgency.travelAgency.domain.reservation.entity.Reservations;
import com.travelAgency.travelAgency.domain.reservation.repository.ReservationRepository;
import com.travelAgency.travelAgency.domain.room.entity.Rooms;
import com.travelAgency.travelAgency.domain.room.repository.RoomRepository;
import com.travelAgency.travelAgency.domain.stock.entity.Stocks;
import com.travelAgency.travelAgency.domain.stock.repository.StockRepository;
import com.travelAgency.travelAgency.domain.user.entity.Users;
import com.travelAgency.travelAgency.domain.user.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationService {

	private final ReservationRepository reservationRepository;
	private final HotelRepository hotelRepository;
	private final RoomRepository roomRepository;
	private final UserRepository userRepository;
	private final StockRepository stockRepository;
	public ResponseEntity<String> enrollReservation(
		Principal principal,
		long hotelId,
		long roomId,
		ReservationRequest reservationRequest
	) {

		Rooms room = roomRepository.findById(roomId).orElseThrow(() -> new NormalException(ErrorCode.NO_ROOM_ID));
		Users user = userRepository.findByEmail(principal.getName()).orElseThrow(()-> new NormalException(ErrorCode.NO_USER_EMAIL));
		Stocks stock = stockRepository.findByRooms(room);

		Reservations newReservations = new Reservations(
			reservationRequest.getTravelers(),
			reservationRequest.getCheckIn(),
			reservationRequest.getCheckOut(),
			reservationRequest.getName(),
			reservationRequest.getPhoneNumber(),
			reservationRequest.getEmail(),
			"NOT PAY",
			user.getId().toString() + new Date(),
			user,
			room
		);

		reservationRepository.save(newReservations);

		stock.decreaseRoomStocks();

		return ResponseEntity.ok("Success");
	}

	public ResponseEntity<ReservationToEntity> getReservationInfo(long reservationID) {

		Reservations reservation = reservationRepository.getReferenceById(reservationID);
		ReservationToEntity reservationToEntity = ReservationMapper.INSTANCE.reservationToEntity(reservation);

		return ResponseEntity.ok(reservationToEntity);
	}
}
