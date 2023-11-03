package com.travelAgency.travelAgency.domain.travel.controller;


import com.travelAgency.travelAgency.domain.travel.dto.CreateTravelDTO;
import com.travelAgency.travelAgency.domain.travel.dto.DetailTravelDTO;
import com.travelAgency.travelAgency.domain.travel.dto.UpDateTravelDTO;
import com.travelAgency.travelAgency.domain.travel.entity.Travel;
import com.travelAgency.travelAgency.domain.travel.service.TravelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.travelAgency.travelAgency.domain.travel.dto.CreateTravelDTO.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TravelController {

    private final TravelService travelService;
    @PostMapping("/travel")
    public ResponseEntity<ResponseTravelDTO> createTravel(
            @RequestBody CreateTravelDTO.RequestTravelDTO dataInfo
        ){


        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(travelService.createTravel(dataInfo));

    }

    @GetMapping("/travel/{travelID}")
    public DetailTravelDTO.ResponseDetailTravelDTO getSpecificTravel(
            @PathVariable Long travelID
    ){
       return travelService.getSpecificTravel(travelID);
    }

    @GetMapping("/travel")
    public List<Travel> getAllTravels(){
        return travelService.getAllTravels();
    }

    @PutMapping("/travel/{travelID}")
    public UpDateTravelDTO updateTravel(
            @PathVariable Long travelID,
            @RequestBody UpDateTravelDTO upDateTravelDTO
    ) {
        return travelService.updateTravel(travelID, upDateTravelDTO);
    }

}
