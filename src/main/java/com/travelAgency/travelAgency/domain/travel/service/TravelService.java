package com.travelAgency.travelAgency.domain.travel.service;


import com.travelAgency.travelAgency.domain.common.Exception;
import com.travelAgency.travelAgency.domain.itinerary.dto.CreateItineraryDTO;
import com.travelAgency.travelAgency.domain.itinerary.entity.Itinerary;
import com.travelAgency.travelAgency.domain.travel.dto.CreateTravelDTO;
import com.travelAgency.travelAgency.domain.travel.dto.DetailTravelDTO;
import com.travelAgency.travelAgency.domain.travel.dto.UpDateTravelDTO;
import com.travelAgency.travelAgency.domain.travel.entity.Travel;
import com.travelAgency.travelAgency.domain.travel.repository.TravelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.travelAgency.travelAgency.domain.travel.dto.CreateTravelDTO.*;

@Service
@Transactional
@RequiredArgsConstructor
public class TravelService {

    private final TravelRepository travelRepository;

    public ResponseTravelDTO createTravel(CreateTravelDTO.RequestTravelDTO dataInfo) {

        return Optional.of(travelRepository.save(CreateTravelDTO.RequestTravelDTO.toEntity(dataInfo)))
                .map(ResponseTravelDTO::fromEntity)
                .orElseThrow();
    }

    public DetailTravelDTO.ResponseDetailTravelDTO getSpecificTravel(Long travelID) {

        return travelRepository.findById(travelID)
                .map(DetailTravelDTO.ResponseDetailTravelDTO::DetailTravelDTOFromEntity)
                .orElseThrow(() -> new Exception("해당 아이디가 없습니다. "));
    }

//    public UpDateTravelDTO updateTravel(Long travelID, UpDateTravelDTO upDateTravelDTO) {
//
//        Travel travel = travelRepository.findById(travelID).orElseThrow(()->new Exception("해당 아이디가 없습니다"));
//
//        travel.updateTravelInfo(upDateTravelDTO.getTripName(), upDateTravelDTO.getDepartTime(), upDateTravelDTO.getArrivedTime(), upDateTravelDTO.getIsDomestic());
//
//        return UpDateTravelDTO.responseUpdateTravel(travel);
//    }

    public List<Travel> getAllTravels() {

        return travelRepository.findAll();

    }
}
