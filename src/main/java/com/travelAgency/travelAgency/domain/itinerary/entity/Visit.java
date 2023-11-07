package com.travelAgency.travelAgency.domain.itinerary.entity;

import jakarta.persistence.*;
import lombok.*;


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
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "visit", cascade = CascadeType.ALL)
    private Itinerary itinerary;


}
