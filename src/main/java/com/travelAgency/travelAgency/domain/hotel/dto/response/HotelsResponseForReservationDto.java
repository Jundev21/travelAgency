package com.travelAgency.travelAgency.domain.hotel.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HotelsResponseForReservationDto {
	private String address;
	private String name;
	private String city;
	private String country;
}
