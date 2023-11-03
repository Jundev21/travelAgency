package com.travelAgency.travelAgency.domain.itinerary.entity;


import com.travelAgency.travelAgency.domain.travel.entity.Travel;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@Builder
@Setter
@AllArgsConstructor
public class Itinerary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String itineraryName;
    @ManyToOne(fetch = FetchType.LAZY)
    private Travel travel;
    @OneToOne(fetch = FetchType.LAZY)
    private Transfortation transfortation;
    @OneToOne(fetch = FetchType.LAZY)
    private Hotel hotel;
    @OneToOne(fetch = FetchType.LAZY)
    private Visit visit;

}
