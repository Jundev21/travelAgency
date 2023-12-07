package com.travelAgency.travelAgency.domain.user.dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.travelAgency.travelAgency.domain.user.dto.response.LoginResponseDTO;
import com.travelAgency.travelAgency.domain.user.dto.response.RegisterResponseDTO;

@Mapper(componentModel = "spring")
public interface UserMapper {
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	RegisterResponseDTO registerResponseDto(String jwtToken);
	LoginResponseDTO logInResponseDto(String accessToken, String refreshToken);
}
