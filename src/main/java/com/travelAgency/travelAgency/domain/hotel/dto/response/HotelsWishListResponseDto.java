package com.travelAgency.travelAgency.domain.hotel.dto.response;

import java.util.ArrayList;
import java.util.List;

import com.travelAgency.travelAgency.domain.hotel.entity.HotelsImages;
import com.travelAgency.travelAgency.domain.room.entity.Rooms;
import com.travelAgency.travelAgency.domain.wishList.entity.WishLists;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HotelsWishListResponseDto {

	private String address;
	private String name;
	private String stars;
	private String city;
	private String country;
	private List<Rooms> roomsList;
	private List<HotelsImages> hotelsImagesList;
	private WishLists wishLists;
}
