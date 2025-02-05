package com.infy.banking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorInformation {

    String errorMessage;

    Integer errorCode;

    LocalDateTime errorTimeStamp;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public LocalDateTime getErrorTimeStamp() {
        return errorTimeStamp;
    }

    public void setErrorTimeStamp(LocalDateTime errorTimeStamp) {
        this.errorTimeStamp = errorTimeStamp;
    }
}
