package com.travelAgency.travelAgency.domain.itinerary.dto;

import com.travelAgency.travelAgency.domain.itinerary.entity.Hotel;
import com.travelAgency.travelAgency.domain.itinerary.entity.Itinerary;
import com.travelAgency.travelAgency.domain.itinerary.entity.Transfortation;
import com.travelAgency.travelAgency.domain.itinerary.entity.Visit;
import com.travelAgency.travelAgency.domain.travel.entity.Travel;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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

        private Long id;
        private String itineraryName;
        private HotelDTO hotel;
        private TransfortationDTO transfortation;
        private VisitDTO visit;

        public static InnerItineraryDTO fromEntity(Itinerary dataInfo){
            return InnerItineraryDTO.builder()
                    .id(dataInfo.getId())
                    .itineraryName(dataInfo.getItineraryName())
                    .hotel(HotelDTO.fromEntity(dataInfo.getHotel()))
                    .transfortation(TransfortationDTO.fromEntity(dataInfo.getTransfortation()))
                    .visit(VisitDTO.fromEntity(dataInfo.getVisit()))
                    .build();

        }

    }

    @Builder
    @Getter
    public static class HotelDTO{
        private Long id;
        private String hotelName;
        private String checkInTime;
        private String checkOutTime;


        public static HotelDTO fromEntity(Hotel hotel){
            return HotelDTO.builder()
                    .id(hotel.getId())
                    .hotelName(hotel.getHotelName())
                    .checkInTime(hotel.getCheckInTime())
                    .checkOutTime(hotel.getCheckOutTime())
                    .build();

        }



    }

    @Builder
    @Getter
    public static class TransfortationDTO{

        private Long id;
        private String fromPlace;
        private String toPlace;
        private String departTime;
        private String arriveTime;


        public static TransfortationDTO fromEntity(Transfortation transfortation){
            return TransfortationDTO.builder()
                    .id(transfortation.getId())
                    .fromPlace(transfortation.getFromPlace())
                    .toPlace(transfortation.getToPlace())
                    .departTime(transfortation.getDepartTime())
                    .arriveTime(transfortation.getArriveTime())
                    .build();

        }



    }

    @Builder
    @Getter
    public static class VisitDTO{

        private Long id;
        private String placeName;
        private String departTime;
        private String arriveTime;


        public static VisitDTO fromEntity(Visit visit){
            return VisitDTO.builder()
                    .id(visit.getId())
                    .placeName(visit.getPlaceName())
                    .departTime(visit.getDepartTime())
                    .arriveTime(visit.getArriveTime())
                    .build();

        }



    }

}

