package com.travelAgency.travelAgency.domain.travel.dto;

import com.travelAgency.travelAgency.domain.travel.entity.Travel;
import lombok.*;

import java.time.LocalTime;


@Getter

public class CreateTravelDTO {

    @Getter
    public static class RequestTravelDTO{

        private String tripName;
        private String departTime;
        private String arrivedTime;
        private Boolean isDomestic= false;

        public static Travel toEntity(RequestTravelDTO request) {
            return Travel.builder()
                    .tripName(request.getTripName())
                    .departTime(request.getDepartTime())
                    .arrivedTime(request.getArrivedTime())
                    .isDomestic(request.getIsDomestic())
                    .build();
        }
    }

    //response 값으로 어떤 데이터들을 전달할 것인가?????

    @Builder
    @Getter
    public static class ResponseTravelDTO{
        protected Long tripId;
        private String tripName;
        private String departTime;
        private String arrivedTime;
        private Boolean isDomestic= false;

        public static ResponseTravelDTO fromEntity(Travel request) {
            return ResponseTravelDTO.builder()
                    .tripId(request.getId())
                    .tripName(request.getTripName())
                    .departTime(request.getDepartTime())
                    .arrivedTime(request.getArrivedTime())
                    .isDomestic(request.getIsDomestic())
                    .build();
        }
    }
}

