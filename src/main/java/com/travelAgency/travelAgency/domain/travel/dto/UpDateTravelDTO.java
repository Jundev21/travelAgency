package com.travelAgency.travelAgency.domain.travel.dto;

import com.travelAgency.travelAgency.domain.travel.entity.Travel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class UpDateTravelDTO {

    private String tripName;
    private String departTime;
    private String arrivedTime;
    private Boolean isDomestic= false;

    public static UpDateTravelDTO responseUpdateTravel(Travel travel){

       return UpDateTravelDTO.builder()
                .tripName(travel.getTripName())
                .departTime(travel.getDepartTime())
                .arrivedTime(travel.getArrivedTime())
                .isDomestic(travel.getIsDomestic())
                .build();


    }
}
