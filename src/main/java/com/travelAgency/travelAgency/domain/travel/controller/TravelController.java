package com.travelAgency.travelAgency.domain.travel.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TravelController {


	@GetMapping("/travel")
	public ResponseEntity<String> getTravel(){

		return ResponseEntity.ok("Get travel information");
	}
}
