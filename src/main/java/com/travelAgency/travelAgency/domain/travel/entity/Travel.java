package com.travelAgency.travelAgency.domain.travel.entity;


import com.travelAgency.travelAgency.domain.itinerary.entity.Itinerary;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access=AccessLevel.PROTECTED)
public class Travel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tripName;
    private String departTime;
    private String arrivedTime;
    private Boolean isDomestic= false;
    @OneToMany(mappedBy = "travel")
    private List<Itinerary> itineraries = new ArrayList<>();




    @Builder
    public Travel(
        String tripName,
        String departTime,
        String arrivedTime,
        Boolean isDomestic
                ){
            this.tripName = tripName;
            this.departTime = departTime;
            this.arrivedTime = arrivedTime;
            this.isDomestic = isDomestic;
    }

    public void updateTravelInfo(String tripName, String departTime, String arrivedTime, Boolean isDomestic){
        this.tripName = tripName;
        this.departTime = departTime;
        this.arrivedTime = arrivedTime;
        this.isDomestic = isDomestic;
    }


}
