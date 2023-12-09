package com.travelAgency.travelAgency.domain.hotel.dto.response;

import java.util.List;

import com.travelAgency.travelAgency.domain.hotel.entity.HotelsImages;
import com.travelAgency.travelAgency.domain.room.dto.response.RoomResponseDto;

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
	private int pageNo;
	private int pageSize;
	private long totalElements;
	private int totalPages;
	private boolean lastPage;
	private List<HotelsImagesDto> hotelsImagesDtoList;

}
