package com.travelAgency.travelAgency.domain.itinerary.dto;

import com.travelAgency.travelAgency.domain.itinerary.entity.Itinerary;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;




@Getter
public class CreateItineraryDTO {

    @Getter
    public static class RequestCreateItineraryDTO{
        private List<Itinerary> itineraryList;
    }

    @Getter
    @Builder
    public static class InnerItineraryDTO {

        private String itineraryName;


        public static InnerItineraryDTO fromEntity(Itinerary dataInfo){
            return InnerItineraryDTO.builder()
                    .itineraryName(dataInfo.getItineraryName())
                    .build();

        }

    }

}

