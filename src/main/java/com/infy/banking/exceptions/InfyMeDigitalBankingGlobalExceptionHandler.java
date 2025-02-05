package com.infy.banking.exceptions;

import com.infy.banking.enums.ExceptionConstants;
import com.infy.banking.model.ErrorInformation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class InfyMeDigitalBankingGlobalExceptionHandler {
    @ExceptionHandler(InfyMeMobileException.class)
    public ResponseEntity<ErrorInformation> handleNullPointerException(InfyMeMobileException im) {

        ErrorInformation ei = new ErrorInformation();
        ei.setErrorCode(200);
        ei.setErrorMessage(im.getMessage());
        ei.setErrorTimeStamp(LocalDateTime.now());
        return new ResponseEntity<ErrorInformation>(ei, HttpStatus.OK);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleNullPointerException(MethodArgumentNotValidException ma) {

        return new ResponseEntity<String>(ma.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleNullPointerException(ConstraintViolationException cv) {

        return new ResponseEntity<String>(cv.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleNullPointerException(NullPointerException ec) {

        return new ResponseEntity<String>(ec.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

