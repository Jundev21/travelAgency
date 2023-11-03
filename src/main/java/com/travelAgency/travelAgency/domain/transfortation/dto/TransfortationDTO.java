package com.travelAgency.travelAgency.domain.transfortation.dto;


import com.travelAgency.travelAgency.domain.transfortation.entity.Transfortation;
import lombok.Builder;

@Builder
public class TransfortationDTO {

    protected Long transfortationID;
    private String fromPlace;
    private String toPlace;
    private String departTime;
    private String arriveTime;


    public static TransfortationDTO fromEntity(Transfortation transfortation){

        return TransfortationDTO.builder()
                .transfortationID(transfortation.getId())
                .fromPlace(transfortation.getFromPlace())
                .toPlace(transfortation.getToPlace())
                .departTime(transfortation.getDepartTime())
                .arriveTime(transfortation.getArriveTime())
                .build();

    }

}
