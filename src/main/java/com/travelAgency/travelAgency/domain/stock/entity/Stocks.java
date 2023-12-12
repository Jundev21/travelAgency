package com.travelAgency.travelAgency.domain.stock.entity;

import java.time.LocalDate;

import org.hibernate.annotations.ColumnDefault;

import com.travelAgency.travelAgency.domain.room.entity.Rooms;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Entity
@Getter
public class Stocks {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate date;
	private int roomStocks;
	@ManyToOne(fetch = FetchType.LAZY)
	private Rooms rooms;

	public Stocks(
		LocalDate date,
		int roomStocks,
		Rooms room
	){
		this.date = date;
		this.roomStocks = roomStocks;
		this.rooms = room;
	}

	public void increaseRoomStocks(){
			this.roomStocks += 1;
	}

	public void decreaseRoomStocks(){
		if(roomStocks > 0) {
			this.roomStocks -= 1;
		}
	}
}
