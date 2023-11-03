package com.travelAgency.travelAgency.domain.travel.dto;

import com.travelAgency.travelAgency.domain.itinerary.entity.Itinerary;
import com.travelAgency.travelAgency.domain.travel.entity.Travel;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;



@Getter
@Setter
@Builder
public class AllTravelDTO {

    protected Long tripId;
    private String tripName;
    private String departTime;
    private String arrivedTime;
    private Boolean isDomestic= false;
    private List<Itinerary> itineraries = new ArrayList<>();

    public AllTravelDTO allTravelDTO(Travel travel){
        return AllTravelDTO.builder()
                .tripId(travel.getId())
                .tripName(travel.getTripName())
                .departTime(travel.getDepartTime())
                .arrivedTime(travel.getArrivedTime())
                .isDomestic(travel.getIsDomestic())
                .itineraries(travel.getItineraries())
                .build();

    }

}
