package com.travelAgency.travelAgency.domain.hotel.dto;

import com.travelAgency.travelAgency.domain.hotel.entity.Hotel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalTime;


@Getter
@Setter
@Builder
@ToString
public class HotelDTO {

    protected Long hotelId;
    private String hotelName;
    private String checkInTime;
    private String checkOutTime;

    private HotelDTO fromEntity(Hotel hotel){
        return HotelDTO.builder()
                .hotelId(hotel.getId())
                .hotelName(hotel.getHotelName())
                .checkInTime(hotel.getCheckInTime())
                .checkOutTime(hotel.getCheckOutTime())
                .build();
    }

}
