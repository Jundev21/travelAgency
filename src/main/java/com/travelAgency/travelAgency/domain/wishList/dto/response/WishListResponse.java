package com.travelAgency.travelAgency.domain.wishList.dto.response;

import com.travelAgency.travelAgency.domain.hotel.dto.response.HotelsResponseForReservationDto;
import com.travelAgency.travelAgency.domain.hotel.dto.response.HotelsWishListResponseDto;
import com.travelAgency.travelAgency.domain.hotel.entity.Hotels;
import com.travelAgency.travelAgency.domain.user.dto.response.BasicUserInfoDto;
import com.travelAgency.travelAgency.domain.user.entity.Users;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WishListResponse {
	private BasicUserInfoDto users;
	private HotelsResponseForReservationDto hotels;

}
