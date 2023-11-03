package com.travelAgency.travelAgency.domain.visit.dto;

import com.travelAgency.travelAgency.domain.itinerary.entity.Itinerary;
import com.travelAgency.travelAgency.domain.visit.entity.Visit;
import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalTime;


@Builder
public class VisitDTO {

    protected Long visitID;
    private String placeName;
    private String departTime;
    private String arriveTime;

    public static VisitDTO fromEntity(Visit visit){

        return VisitDTO.builder()
                .visitID(visit.getId())
                .arriveTime(visit.getArriveTime())
                .departTime(visit.getDepartTime())
                .placeName(visit.getPlaceName())
                .build();



    }

}
