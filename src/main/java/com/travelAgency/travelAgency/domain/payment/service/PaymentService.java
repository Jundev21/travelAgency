package com.travelAgency.travelAgency.domain.payment.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.travelAgency.travelAgency.common.error.ErrorCode;
import com.travelAgency.travelAgency.common.exception.NormalException;
import com.travelAgency.travelAgency.domain.payment.dto.PaymentMapper;
import com.travelAgency.travelAgency.domain.payment.dto.request.PaymentRequestDto;
import com.travelAgency.travelAgency.domain.payment.dto.response.PaymentResponseDto;
import com.travelAgency.travelAgency.domain.payment.entity.Payments;
import com.travelAgency.travelAgency.domain.payment.repository.PaymentRepository;
import com.travelAgency.travelAgency.domain.reservation.entity.Reservations;
import com.travelAgency.travelAgency.domain.reservation.repository.ReservationRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PaymentService {

	private final PaymentRepository paymentRepository;
	private final ReservationRepository reservationRepository;

	public ResponseEntity<String> postPaymentInfo(
		PaymentRequestDto paymentRequestDto,
		long reservationId
	) {
		Reservations reservations = reservationRepository.findById(reservationId).orElseThrow(
			() -> new NormalException(ErrorCode.NO_RESERVATION_ID)
		);

		//payment method 지불로 업데이터하여 저장.
		reservations.updatePayStatus();
		Payments newPayments = PaymentMapper.INSTANCE.paymentToEntity(
			paymentRequestDto,
			reservations
			);
		// 양방향 저장 다시한번 확인
		paymentRepository.save(newPayments);
		return ResponseEntity.ok("Success to pay");
	}

	public ResponseEntity<PaymentResponseDto> getPaymentInfo(long paymentId) {
		Payments payments = paymentRepository.findById(paymentId).orElseThrow(
			() -> new NormalException(ErrorCode.NO_RESERVATION_ID)
		);
		return ResponseEntity.ok(PaymentMapper.INSTANCE.entityToPaymentResponseDto(payments));

	}
}
