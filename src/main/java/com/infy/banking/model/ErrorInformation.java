package com.infy.banking.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorInformation {

    String errorMessage;

    Integer errorCode;

    LocalDateTime errorTimeStamp;
}
