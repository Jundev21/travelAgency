package com.travelAgency.travelAgency.domain.room.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RoomResponseForReservationDto {
	private String name;
	private int travelers;
	private int price;
}
