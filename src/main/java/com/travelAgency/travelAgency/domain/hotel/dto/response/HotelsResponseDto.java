package com.travelAgency.travelAgency.domain.hotel.dto.response;

import java.util.List;

import com.travelAgency.travelAgency.domain.hotel.entity.HotelsImages;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HotelsResponseDto {

	private String address;
	private String name;
	private String stars;
	private String city;
	private String country;
	private String lowestPrice;
	private List<HotelsImagesDto> hotelsImagesDtoList;

}
