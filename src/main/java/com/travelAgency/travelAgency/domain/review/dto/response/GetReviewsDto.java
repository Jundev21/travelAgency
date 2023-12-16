package com.travelAgency.travelAgency.domain.review.dto.response;

import java.time.LocalDateTime;

import com.travelAgency.travelAgency.domain.hotel.entity.Hotels;
import com.travelAgency.travelAgency.domain.user.entity.Users;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetReviewsDto {
	@NotBlank
	private String title;
	@NotBlank
	private String content;
	@NotBlank
	private double reviewRates;
	private Hotels hotels;
	private Users users;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
