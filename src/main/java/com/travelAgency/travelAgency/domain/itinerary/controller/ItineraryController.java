package com.travelAgency.travelAgency.domain.itinerary.controller;


import com.travelAgency.travelAgency.domain.itinerary.dto.CreateItineraryDTO;
import com.travelAgency.travelAgency.domain.itinerary.service.ItineraryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ItineraryController {

    private final ItineraryService itineraryService;

    @PostMapping("/travel/{travelID}/itinerary")
    public List<CreateItineraryDTO.InnerItineraryDTO> createItinerary(
            @PathVariable Long travelID,
            @RequestBody CreateItineraryDTO.RequestCreateItineraryDTO createItineraryDTO
    ){

        return itineraryService.createItinerary(travelID, createItineraryDTO);

    }




}

