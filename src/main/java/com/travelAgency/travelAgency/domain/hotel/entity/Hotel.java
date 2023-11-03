package com.travelAgency.travelAgency.domain.hotel.entity;

import com.travelAgency.travelAgency.domain.itinerary.entity.Itinerary;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;


@Entity
@NoArgsConstructor
@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String hotelName;
    private String checkInTime;
    private String checkOutTime;

//    @OneToOne(mappedBy = "hotel")
//    private Itinerary itinerary;
}
