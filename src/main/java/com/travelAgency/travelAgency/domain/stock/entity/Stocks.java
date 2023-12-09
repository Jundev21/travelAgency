package com.travelAgency.travelAgency.domain.stock.entity;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Stocks {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private int roomStocks;
}
