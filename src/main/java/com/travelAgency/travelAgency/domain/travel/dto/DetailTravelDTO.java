package com.travelAgency.travelAgency.domain.travel.dto;


import com.travelAgency.travelAgency.domain.itinerary.dto.CreateItineraryDTO;
import com.travelAgency.travelAgency.domain.itinerary.entity.Itinerary;
import com.travelAgency.travelAgency.domain.travel.entity.Travel;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Getter
public class DetailTravelDTO {



    @Builder
    public static class ResponseDetailTravelDTO{
        protected Long tripId;
        private String tripName;
        private String departTime;
        private String arrivedTime;
        private Boolean isDomestic= false;
        private List<Itinerary> itineraries = new ArrayList<>();


        public static ResponseDetailTravelDTO DetailTravelDTOFromEntity(Travel travelDTO){
            return ResponseDetailTravelDTO.builder()
                    .tripId(travelDTO.getId())
                    .tripName(travelDTO.getTripName())
                    .departTime(travelDTO.getDepartTime())
                    .arrivedTime(travelDTO.getArrivedTime())
                    .isDomestic(travelDTO.getIsDomestic())
                    .itineraries(travelDTO.getItineraries().stream()
                            .map((e)-> Itinerary.builder()
                                    .id(e.getId())
                                    .itineraryName(e.getItineraryName())
                                    .build()).collect(Collectors.toList())
                    )
                    .build();
        }

    }

}
