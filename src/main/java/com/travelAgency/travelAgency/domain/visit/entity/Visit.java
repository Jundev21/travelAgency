package com.travelAgency.travelAgency.domain.visit.entity;

import com.travelAgency.travelAgency.domain.itinerary.entity.Itinerary;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;


@Entity
@NoArgsConstructor
@ToString
@Setter
@Getter
@Builder
@AllArgsConstructor
public class Visit {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String placeName;
    private String departTime;
    private String arriveTime;


//    @OneToOne(mappedBy = "visit")
//    private Itinerary itinerary;


}
