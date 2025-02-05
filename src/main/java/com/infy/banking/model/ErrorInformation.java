package com.infy.banking.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


public class ErrorInformation {

    String errorMessage;

    Integer errorCode;

    LocalDateTime errorTimeStamp;

    public String getErrorMessage() {
        return errorMessage;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public LocalDateTime getErrorTimeStamp() {
        return errorTimeStamp;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrorTimeStamp(LocalDateTime errorTimeStamp) {
        this.errorTimeStamp = errorTimeStamp;
    }
}
