package com.infy.banking.controller;

import com.infy.banking.dto.BankAccountDTO;
import com.infy.banking.dto.LinkingAcountDTO;
import com.infy.banking.exceptions.InfyMeMobileException;
import com.infy.banking.service.BankAccountDetailsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpClient;
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
    @PostMapping("/accounts")
    public ResponseEntity<String> createAccount(@RequestBody @Valid BankAccountDTO account) throws MethodArgumentNotValidException {
        return new ResponseEntity<String>(bankAccountDetailsService.createAccount(account), HttpStatus.OK);
    }

    @PostMapping("/accounts/{mobileNumber}")
    public ResponseEntity<String> linkAccount(@RequestBody @Valid LinkingAcountDTO link,@PathVariable Long mobileNumber) throws MethodArgumentNotValidException, InfyMeMobileException {
        if(link.getOtp()==null) {
            return new ResponseEntity<String>(bankAccountDetailsService.linkAccount(link, mobileNumber), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<String>(bankAccountDetailsService.linkAccountWithOTP(link, mobileNumber), HttpStatus.OK);
        }
    }

}
