package com.travelAgency.travelAgency.domain.room.dto.response;

import com.travelAgency.travelAgency.domain.hotel.entity.Hotels;

import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RoomResponseDto {
	private String name;
	private int travelers;
	private String price;
	private String bedNum;
	private String roomSize;
	private Boolean wifi;
	private Boolean tv;
	private Boolean shower;
	private Boolean food;
	private Boolean airCondition;
	private Hotels hotels;
}
