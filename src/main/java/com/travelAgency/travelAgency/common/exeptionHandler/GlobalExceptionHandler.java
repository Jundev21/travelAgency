package com.travelAgency.travelAgency.common.exeptionHandler;

import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
@Order(value =Integer.MAX_VALUE) //값이 낮을수록 가장 먼저 실행되고 높을수록 늦게 실행된다. , Max_Value 가장 마지막에 실행된다.

public class GlobalExceptionHandler {

	public void exception(){

	}
}
