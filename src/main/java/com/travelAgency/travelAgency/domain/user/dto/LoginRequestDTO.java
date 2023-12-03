package com.travelAgency.travelAgency.domain.user.dto;

import com.travelAgency.travelAgency.domain.user.entity.Users;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class LoginRequestDTO {

    private String email;
    private String password;

    public LoginRequestDTO fromEntity(Users users){
        return LoginRequestDTO.builder()
            .email(users.getEmail())
            .password(users.getPassword())
            .build();

    }
}
