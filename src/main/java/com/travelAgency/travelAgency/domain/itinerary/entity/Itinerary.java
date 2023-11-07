package com.travelAgency.travelAgency.domain.itinerary.entity;


import com.travelAgency.travelAgency.domain.travel.entity.Travel;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access=AccessLevel.PROTECTED)
public class Itinerary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String itineraryName;
    @ManyToOne(fetch = FetchType.LAZY)
    private Travel travel;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Transfortation transfortation;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Hotel hotel;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Visit visit;

    @Builder
    public Itinerary(
            Long id,
            String itineraryName
    ){
        this.id = id;
        this.itineraryName = itineraryName;
    }


    @Builder
    public Itinerary(
            String itineraryName,
            Travel travel,
            Transfortation transfortation,
            Hotel hotel,
            Visit visit
    ){
        this.itineraryName = itineraryName;
        this.travel = travel;
        this.hotel = hotel;
        this.transfortation = transfortation;
        this.visit = visit;
    }


}
