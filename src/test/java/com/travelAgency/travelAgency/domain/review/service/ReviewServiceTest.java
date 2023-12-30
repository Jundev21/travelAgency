package com.travelAgency.travelAgency.domain.review.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.test.context.support.WithMockUser;

import com.travelAgency.travelAgency.domain.hotel.entity.Hotels;
import com.travelAgency.travelAgency.domain.hotel.repository.HotelRepository;
import com.travelAgency.travelAgency.domain.review.dto.ReviewMapper;
import com.travelAgency.travelAgency.domain.review.dto.request.ReviewRequestDto;
import com.travelAgency.travelAgency.domain.review.entity.Reviews;
import com.travelAgency.travelAgency.domain.review.repository.ReviewRepository;
import com.travelAgency.travelAgency.domain.user.entity.Role;
import com.travelAgency.travelAgency.domain.user.entity.Users;

import jakarta.transaction.Transactional;

@Transactional
@ExtendWith(MockitoExtension.class)
class ReviewServiceTest {

	@InjectMocks
	private ReviewService reviewService;
	@Mock
	private ReviewRepository reviewRepository;
	@Mock
	private HotelRepository hotelRepository;

	@Test
	@DisplayName("리뷰 등록 성공")
	@WithMockUser
	public void createReview(){

		ReviewRequestDto reviewRequestDto = ReviewRequestDto.builder()
			.title("title")
			.content("content")
			.reviewRates(10)
			.build();
		Hotels hotel = new Hotels("test", "test", "test", "test", "test");
		Hotels hotel2 = new Hotels("test", "test", "test", "test", "test");

		hotelRepository.save(hotel);
		hotelRepository.save(hotel2);

		System.out.println(hotel2.getId());

		// given(hotelRepository.findById(anyLong())).willReturn(Optional.of(hotel));

		Reviews newReview =
			new Reviews("title", "content", 10,
				new Users("kim",20,"test","asdfasdfasdf", Role.USER), hotel);

		reviewRepository.save(newReview);



		System.out.println(reviewRepository.findAll());



	}

}