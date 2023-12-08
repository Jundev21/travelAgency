package com.travelAgency.travelAgency.domain.hotel.dto.response;

import com.travelAgency.travelAgency.domain.hotel.entity.Hotels;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HotelsImagesDto {
	private Hotels hotels;
	private String imageUrl;
}
