package com.travelAgency.travelAgency.domain.review.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelAgency.travelAgency.domain.review.dto.request.ReviewRequestDto;
import com.travelAgency.travelAgency.domain.review.dto.response.GetReviewsDto;
import com.travelAgency.travelAgency.domain.review.service.ReviewService;
import com.travelAgency.travelAgency.domain.user.entity.Users;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ReviewController {

	private final ReviewService reivewService;
	//리뷰 작성, 조회, 수정, 삭제

	@PostMapping("/hotel/{hotelId}/review")
	public ResponseEntity<String> writeReview(
		@PathVariable long hotelId,
		@AuthenticationPrincipal Users users,
		@Validated
		@RequestBody ReviewRequestDto reviewRequestDto
	){
		return reivewService.writeReview(hotelId, reviewRequestDto ,users);
	}

	@GetMapping("/hotel/{hotelId}/review")
	public ResponseEntity<List<GetReviewsDto>> getReview(
		@PathVariable long hotelId
	){
		return reivewService.getReview(hotelId);
	}

	@PatchMapping ("/hotel/{hotelId}/review/{reviewId}")
	public ResponseEntity<String> updateReview(
		@PathVariable long reviewId,
		@Validated
		@RequestBody ReviewRequestDto reviewRequestDto
	){
		return reivewService.updateReview(reviewId, reviewRequestDto);
	}

	@DeleteMapping("/hotel/{hotelId}/review/{reviewId}")
	public ResponseEntity<String> deleteReview(
		@PathVariable long reviewId,
		@AuthenticationPrincipal Users users
	){
		return reivewService.deleteReview(reviewId, users);
	}

}
