package com.travelAgency.travelAgency.domain.wishList.dto;

import org.apache.catalina.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.travelAgency.travelAgency.domain.hotel.entity.Hotels;
import com.travelAgency.travelAgency.domain.user.entity.Users;
import com.travelAgency.travelAgency.domain.wishList.dto.response.WishListResponse;
import com.travelAgency.travelAgency.domain.wishList.entity.WishLists;

@Mapper(componentModel = "spring")
public interface WishListWrapper {
	WishListWrapper INSTANCE = Mappers.getMapper(WishListWrapper.class);

	WishLists wishListToEntity(Users users, Hotels hotels);

	WishListResponse wishListResponse(WishLists wishLists);
}
