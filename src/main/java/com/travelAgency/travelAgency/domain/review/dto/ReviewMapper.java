package com.travelAgency.travelAgency.domain.review.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.travelAgency.travelAgency.domain.hotel.entity.Hotels;
import com.travelAgency.travelAgency.domain.review.dto.request.ReviewRequestDto;
import com.travelAgency.travelAgency.domain.review.dto.response.GetReviewsDto;
import com.travelAgency.travelAgency.domain.review.entity.Reviews;
import com.travelAgency.travelAgency.domain.user.entity.Users;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

	ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);


	@Mapping(source = "reviews.users", target = "users")
	GetReviewsDto getReviewDto(Reviews reviews);
	Reviews toEntity(ReviewRequestDto getReviewsDto, Hotels hotels, Users users);

}
