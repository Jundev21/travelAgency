package com.travelAgency.travelAgency.domain.itinerary.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@NoArgsConstructor
@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor

public class Transfortation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fromPlace;
    private String toPlace;
    private String departTime;
    private String arriveTime;
    @OneToOne(mappedBy = "transfortation")
    private Itinerary itinerary;
}
