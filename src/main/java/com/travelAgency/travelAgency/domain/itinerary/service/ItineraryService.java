package com.travelAgency.travelAgency.domain.itinerary.service;

import com.travelAgency.travelAgency.domain.common.Exception;
import com.travelAgency.travelAgency.domain.itinerary.entity.Itinerary;
import com.travelAgency.travelAgency.domain.itinerary.repository.ItineraryRepository;
import com.travelAgency.travelAgency.domain.travel.entity.Travel;
import com.travelAgency.travelAgency.domain.travel.repository.TravelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.travelAgency.travelAgency.domain.itinerary.dto.CreateItineraryDTO.InnerItineraryDTO;
import static com.travelAgency.travelAgency.domain.itinerary.dto.CreateItineraryDTO.RequestCreateItineraryDTO;


@Service
@Transactional
@RequiredArgsConstructor
public class ItineraryService {

    private final TravelRepository travelRepository;
    private final ItineraryRepository itineraryRepository;

    //문제점이 무엇인가
    // 리퀘스트에 데이터가 여러 배열 형태로 들어왔을때 어떻게 모든 데이터들을 저장하고 응답을 내릴것인지.
    // 리쿼스트 객체형태 키값이 "itineraryNameList"
    // 테이블의 아이디는 데이터에 저장되는 순간 생성된다.

    public List<InnerItineraryDTO> createItinerary(Long travelID, RequestCreateItineraryDTO createItineraryDTO) {
        Travel travelById = travelRepository.findById(travelID).orElseThrow(() -> new Exception("해당 여행이 없습니다"));
        List<Itinerary> newItineraryDTO = new ArrayList<>();
        List<InnerItineraryDTO> responseData = new ArrayList<>();

        for(Itinerary innerItineraryDTO : createItineraryDTO.getItineraryList()){

            Itinerary newInnerDTO = new Itinerary(
                    innerItineraryDTO.getItineraryName(),
                    travelById,
                    innerItineraryDTO.getTransfortation(),
                    innerItineraryDTO.getHotel(),
                    innerItineraryDTO.getVisit()
            );

            InnerItineraryDTO response =  InnerItineraryDTO.fromEntity(innerItineraryDTO);

            responseData.add(response);
            newItineraryDTO.add(newInnerDTO);
        }

        itineraryRepository.saveAll(newItineraryDTO);

        return responseData;
    }
}
