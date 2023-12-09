package com.travelAgency.travelAgency.domain.hotel.dto;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.travelAgency.travelAgency.domain.hotel.dto.response.HotelsDetailsResponseDto;
import com.travelAgency.travelAgency.domain.hotel.dto.response.HotelsImagesDto;
import com.travelAgency.travelAgency.domain.hotel.dto.response.HotelsResponseDto;
import com.travelAgency.travelAgency.domain.hotel.entity.Hotels;
import com.travelAgency.travelAgency.domain.hotel.entity.HotelsImages;

@Mapper(componentModel = "spring")
public interface HotelMapper {
	HotelMapper INSTANCE = Mappers.getMapper(HotelMapper.class);

	@Mapping(source = "hotels.hotelsImagesList", target = "hotelsImagesDtoList")
	HotelsResponseDto hotelResponseDto(
		Hotels hotels,
		String lowestPrice,
		int pageNo,
		int pageSize,
		long totalElements,
		int totalPages,
		boolean lastPage
	);

	HotelsImagesDto hotelsImagesDto(HotelsImages hotelsImages);

	@Mapping(source = "hotels.roomsList", target = "roomsDtoList")
	@Mapping(source = "hotels.hotelsImagesList", target = "hotelsImagesDtoList")
	HotelsDetailsResponseDto hotelDetailsResponseDto(Hotels hotels);

}
