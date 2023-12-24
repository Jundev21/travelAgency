package com.travelAgency.travelAgency.domain.reservation.service;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.travelAgency.travelAgency.common.error.ErrorCode;
import com.travelAgency.travelAgency.common.exception.NormalException;
import com.travelAgency.travelAgency.domain.hotel.entity.Hotels;
import com.travelAgency.travelAgency.domain.hotel.repository.HotelRepository;
import com.travelAgency.travelAgency.domain.payment.payEnum.PayEnum;
import com.travelAgency.travelAgency.domain.reservation.dto.ReservationMapper;
import com.travelAgency.travelAgency.domain.reservation.dto.request.ReservationRequest;
import com.travelAgency.travelAgency.domain.reservation.dto.response.ReservationConformDto;
import com.travelAgency.travelAgency.domain.reservation.dto.response.ReservationToEntity;
import com.travelAgency.travelAgency.domain.reservation.entity.Reservations;
import com.travelAgency.travelAgency.domain.reservation.repository.ReservationRepository;
import com.travelAgency.travelAgency.domain.reservation.reservationEnum.ReservationEnum;
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
		long roomId,
		ReservationRequest reservationRequest
	) {
		// 예약하기 위해서는 해당방이 예약 가능한 방인지를 먼저 확인해야한다.
		Rooms room = roomRepository.findById(roomId).orElseThrow(() -> new NormalException(ErrorCode.NO_ROOM_ID));
		Users user = userRepository.findByEmail(principal.getName()).orElseThrow(()-> new NormalException(ErrorCode.NO_USER_EMAIL));
		Long isAvailable = roomRepository.checkAvailableRooms(
			reservationRequest.getCheckIn(),
			reservationRequest.getCheckOut(),
			reservationRequest.getTravelers(),
			roomId
		);

		if(isAvailable == null){
			throw (new NormalException(ErrorCode.NO_RESERVATION_ID));
		}

		Reservations newReservations = ReservationMapper.INSTANCE.reservationConstructToEntity(
			reservationRequest,
			PayEnum.StandBy,
			user.getId().toString(),
			room,
			user
		);

		reservationRepository.save(newReservations);
		return ResponseEntity.ok(newReservations.getRooms().getHotels().getName() + " 호텔 " + newReservations.getRooms().getName() + " 방이 예약되었습니다 ");
	}

	public ResponseEntity<ReservationConformDto> getReservationInfo(long reservationID, Users users) {
		Reservations reservation = reservationRepository.findById(reservationID).orElseThrow();
		ReservationToEntity reservationToEntity = ReservationMapper.INSTANCE.reservationToEntity(reservation);

		Hotels hotels = hotelRepository.findById(reservation.getRooms().getHotels().getId()).orElseThrow(
			() -> new NormalException(ErrorCode.NO_HOTEL_ID)
		);

		Rooms rooms = roomRepository.findById(reservation.getRooms().getId()).orElseThrow(
			() -> new NormalException(ErrorCode.NO_ROOM_ID)
		);

		ReservationConformDto reservationConformDto = ReservationMapper.INSTANCE.reservationConform(
			reservation,
			hotels,
			rooms

		);

		return ResponseEntity.ok(reservationConformDto);
	}
}



// Mapper 없이 기본생성자로 작성했을때
// Reservations newReservations = new Reservations(
// 	reservationRequest.getTravelers(),
// 	reservationRequest.getCheckIn(),
// 	reservationRequest.getCheckOut(),
// 	reservationRequest.getName(),
// 	reservationRequest.getPhoneNumber(),
// 	reservationRequest.getEmail(),
// 	"NOT PAY",
// 	user.getId().toString() + new Date(),
// 	user,
// 	room
// );