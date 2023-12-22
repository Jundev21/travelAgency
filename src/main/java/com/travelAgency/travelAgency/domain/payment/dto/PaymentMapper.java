package com.travelAgency.travelAgency.domain.payment.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.travelAgency.travelAgency.domain.payment.dto.request.PaymentRequestDto;
import com.travelAgency.travelAgency.domain.payment.dto.response.PaymentResponseDto;
import com.travelAgency.travelAgency.domain.payment.entity.Payments;
import com.travelAgency.travelAgency.domain.reservation.entity.Reservations;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
	PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);
	Payments paymentToEntity(
		PaymentRequestDto paymentRequestDto,
		Reservations reservation
	);
	PaymentResponseDto entityToPaymentResponseDto(Payments payments);
}
