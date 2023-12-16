package com.travelAgency.travelAgency.domain.review.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewRequestDto {
	private String title;
	private String content;
	private double reviewRates;
}
