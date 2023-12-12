package com.travelAgency.travelAgency.domain.common.exception;

import com.travelAgency.travelAgency.domain.common.error.ErrorCode;

import lombok.Getter;
@Getter
public class NormalException extends RuntimeException{

    private final ErrorCode errorCode;
    private final String detailMessage;

    public NormalException(ErrorCode errorCode){
        super(errorCode.getErrorMessage());
        this.errorCode = errorCode;
        this.detailMessage = errorCode.getErrorMessage();
    }
}
