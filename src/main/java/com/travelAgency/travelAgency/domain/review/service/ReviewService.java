package com.travelAgency.travelAgency.domain.review.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.travelAgency.travelAgency.common.error.ErrorCode;
import com.travelAgency.travelAgency.common.exception.NormalException;
import com.travelAgency.travelAgency.domain.hotel.entity.Hotels;
import com.travelAgency.travelAgency.domain.hotel.repository.HotelRepository;
import com.travelAgency.travelAgency.domain.review.dto.ReviewMapper;
import com.travelAgency.travelAgency.domain.review.dto.request.ReviewRequestDto;
import com.travelAgency.travelAgency.domain.review.dto.response.GetReviewsDto;
import com.travelAgency.travelAgency.domain.review.entity.Reviews;
import com.travelAgency.travelAgency.domain.review.repository.ReviewRepository;
import com.travelAgency.travelAgency.domain.user.entity.Users;
import com.travelAgency.travelAgency.domain.user.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {

	private final ReviewRepository reviewRepository;
	private final UserRepository userRepository;
	private final HotelRepository hotelRepository;

	public ResponseEntity<String> writeReview(
		long hotelId,
		ReviewRequestDto reviewRequestDto,
		Users users) {

		Hotels hotelFindById = hotelRepository.findById(hotelId).orElseThrow(
			() -> new NormalException(ErrorCode.NO_HOTEL_ID)
		);
		Users userFindByEmail = userRepository.findByEmail(users.getEmail()).orElseThrow(
			() -> new NormalException(ErrorCode.NO_USER_EMAIL)
		);

		Reviews newReview = ReviewMapper.INSTANCE.toEntity(reviewRequestDto, hotelFindById, userFindByEmail);

		reviewRepository.save(newReview);


		return ResponseEntity.ok("리뷰 등록 성공했습니다.");

	}

	public ResponseEntity<List<GetReviewsDto>> getReview(long hotelId) {
		Hotels hotels = hotelRepository.findById(hotelId).orElseThrow(
			() -> new NormalException(ErrorCode.NO_HOTEL_ID)
		);
		List<Reviews> reviewsList = reviewRepository.findByHotels(hotels).orElseThrow(
			() -> new NormalException(ErrorCode.NO_REVIEW_ID)
		);
		List<GetReviewsDto> getReviewsDto = reviewsList.stream().map(
			ReviewMapper.INSTANCE::getReviewDto
			).collect(Collectors.toList());

		return ResponseEntity.ok(getReviewsDto);
	}

	public ResponseEntity<String> updateReview(
		long reviewId,
		ReviewRequestDto reviewRequestDto) {

		Reviews reviews = reviewRepository.findById(reviewId).orElseThrow(
			() -> new NormalException(ErrorCode.NO_REVIEW_ID)
		);

		if (reviewRequestDto.getReviewRates() != reviews.getReviewRates()) {
			reviews.updateReviewRate(reviewRequestDto.getReviewRates());
		}

		if (!Objects.isNull(reviewRequestDto.getContent() )) {
			reviews.updateContent(reviewRequestDto.getContent());
		}

		if (!Objects.isNull(reviewRequestDto.getTitle())) {
			reviews.updateTitle(reviewRequestDto.getTitle());
		}

		return ResponseEntity.ok("리뷰 수정 성공했습니다.");

	}

	public ResponseEntity<String> deleteReview(long reviewId, Users users) {
		Reviews reviews = reviewRepository.findById(reviewId).orElseThrow(
			() -> new NormalException(ErrorCode.NO_REVIEW_ID)
		);
		reviewRepository.delete(reviews);
		return ResponseEntity.ok("해당 리뷰가 삭제되었습니다.");
	}
}
