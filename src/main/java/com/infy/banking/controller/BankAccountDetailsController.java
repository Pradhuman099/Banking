package com.infy.banking.controller;

import com.infy.banking.dto.BankAccountDTO;
import com.infy.banking.service.BankAccountDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/api/v1")
public class BankAccountDetailsController {
    @Autowired
    private BankAccountDetailsService bankAccountDetailsService;

    @GetMapping(value="/getUser/{mobileNo}")
    public ResponseEntity<List<BankAccountDTO>> listAccounts(Long mobileNo){
        List<BankAccountDTO> data=bankAccountDetailsService.listBankAccountDetailsViaMobileNo(mobileNo);
        return  new ResponseEntity<>(data,HttpStatus.OK);
    }
}
