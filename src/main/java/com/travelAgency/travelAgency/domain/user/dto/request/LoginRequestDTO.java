package com.travelAgency.travelAgency.domain.user.dto.request;

import lombok.Builder;
import lombok.Getter;



@Getter
@Builder
public class LoginRequestDTO {
    private String email;
    private String password;

}
