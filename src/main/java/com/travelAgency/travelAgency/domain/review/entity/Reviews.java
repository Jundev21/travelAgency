package com.travelAgency.travelAgency.domain.review.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.travelAgency.travelAgency.domain.hotel.entity.Hotels;
import com.travelAgency.travelAgency.domain.user.entity.Users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Reviews {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String title;
	@Column(nullable = false)
	private String content;
	@Column(nullable = false)
	private double reviewRates;
	@ManyToOne(fetch = FetchType.LAZY)
	private Hotels hotels;
	@ManyToOne(fetch = FetchType.LAZY)
	private Users users;
	@CreatedDate
	private LocalDateTime createdAt;
	@LastModifiedDate
	private LocalDateTime updatedAt;

	public Reviews(
		String title,
		String content,
		double reviewRates,
		Users users,
		Hotels hotels
	){
		this.title = title;
		this.content = content;
		this.reviewRates = reviewRates;
		this.users = users;
		this.hotels = hotels;
	}


	public void updateReviewRate(double reviewRates){
		this.reviewRates = reviewRates;
	}
	public void updateTitle(String title){
		this.title = title;
	}
	public void updateContent(String content){
		this.content = content;
	}

}
