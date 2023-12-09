package com.travelAgency.travelAgency.domain.common;

import lombok.Getter;
@Getter
public class NormalException extends RuntimeException{

    private final ErrorCode errorCode;
    private final String detailMessage;

    public NormalException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.detailMessage = errorCode.getMessage();
    }
}
