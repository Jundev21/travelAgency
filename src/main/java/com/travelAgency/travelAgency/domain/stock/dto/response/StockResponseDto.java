package com.travelAgency.travelAgency.domain.stock.dto.response;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StockResponseDto {
	private LocalDate date;
	private int roomStocks;

}
