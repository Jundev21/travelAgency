package com.travelAgency.travelAgency.domain.stock.dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.travelAgency.travelAgency.domain.stock.dto.response.StockResponseDto;
import com.travelAgency.travelAgency.domain.stock.entity.Stocks;

@Mapper(componentModel = "spring")
public interface StockMapper {
	StockMapper INSTANCE = Mappers.getMapper(StockMapper.class);

	StockResponseDto stockResponseDto(Stocks stocks);

}
