package com.travelAgency.travelAgency.domain.stock.entity;

import java.time.LocalDate;

import org.hibernate.annotations.ColumnDefault;

import com.travelAgency.travelAgency.domain.room.entity.Rooms;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Stocks {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;
	private LocalDate date;
	private int roomStocks;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rooms_id")
	private Rooms rooms;

	public Stocks(
		LocalDate date,
		int roomStocks
	){
		this.date = date;
		this.roomStocks = roomStocks;
	}

	public void increaseRoomStocks(){
			this.roomStocks += 1;
	}

	public void decreaseRoomStocks(){
		if(this.roomStocks > 0) {
			this.roomStocks -= 1;
		}
	}


	public void addRoom(Rooms rooms){

		this.rooms = rooms;

	}

}
