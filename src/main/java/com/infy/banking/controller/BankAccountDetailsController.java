package com.infy.banking.controller;

import com.infy.banking.dto.BankAccountDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/api/v1")
public class InfyMeDigitalBanking_Accounts_Controller {

    @GetMapping(value="/getUser/{id}")
    public ResponseEntity<List<BankAccountDTO>> listAccounts(Long mobileNo){

    }
}
