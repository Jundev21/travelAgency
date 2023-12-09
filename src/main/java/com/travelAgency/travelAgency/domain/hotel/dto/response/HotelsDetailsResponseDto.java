package com.travelAgency.travelAgency.domain.hotel.dto.response;

import java.util.List;

import com.travelAgency.travelAgency.domain.room.dto.response.RoomResponseDto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HotelsDetailsResponseDto {
	private String address;
	private String name;
	private String stars;
	private String city;
	private String country;
	private List<HotelsImagesDto> hotelsImagesDtoList;
	private List<RoomResponseDto> roomsDtoList;
}
