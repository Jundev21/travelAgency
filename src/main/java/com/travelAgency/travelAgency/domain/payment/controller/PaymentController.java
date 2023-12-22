package com.travelAgency.travelAgency.domain.payment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.travelAgency.travelAgency.domain.payment.dto.request.PaymentRequestDto;
import com.travelAgency.travelAgency.domain.payment.dto.response.PaymentResponseDto;
import com.travelAgency.travelAgency.domain.payment.service.PaymentService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class PaymentController {

	private final PaymentService paymentService;


	@PostMapping("/payment/reservations/{reservationId}")
	public ResponseEntity<String> postPaymentInfo(
		@RequestBody PaymentRequestDto paymentRequestDto,
		@PathVariable long reservationId
	){
		return paymentService.postPaymentInfo(paymentRequestDto,reservationId);
	}

	@GetMapping("/payment/{paymentId}")
	public ResponseEntity<PaymentResponseDto> getPaymentInfo(
		@PathVariable long paymentId
	){

		return paymentService.getPaymentInfo(paymentId);
	}


}
