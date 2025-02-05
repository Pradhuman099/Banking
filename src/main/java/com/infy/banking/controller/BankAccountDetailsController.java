package com.infy.banking.controller;

import com.infy.banking.dto.BankAccountDTO;
import com.infy.banking.dto.TransactionDTO;
import com.infy.banking.enums.ExceptionConstants;
import com.infy.banking.exceptions.InfyMeDigitalBankingGlobalExceptionHandler;
import com.infy.banking.dto.LinkingAcountDTO;
import com.infy.banking.exceptions.InfyMeMobileException;
import com.infy.banking.service.BankAccountDetailsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpClient;
import java.util.List;

import static com.infy.banking.enums.ExceptionConstants.NO_ACCOUNTS_FOUND;
import static com.infy.banking.enums.ExceptionConstants.NO_ACCOUNT_IS_LINKED;
import static com.infy.banking.enums.ExceptionConstants.NO_ACTIVE_TRANSACTIONS;

@RestController
@RequestMapping(value="/api/v1")
public class BankAccountDetailsController {
    @Autowired
    private BankAccountDetailsService bankAccountDetailsService;

    @GetMapping(value="/getUser/{mobileNo}")
    public ResponseEntity<List<BankAccountDTO>> listAccounts(@PathVariable Long mobileNo) throws InfyMeMobileException {
        List<BankAccountDTO> data=bankAccountDetailsService.listBankAccountDetailsViaMobileNo(mobileNo);
        if(data.size()!=0) {
            return new ResponseEntity<>(data, HttpStatus.OK);
        }
        else{
            throw new InfyMeMobileException(HttpStatus.OK.toString(), NO_ACCOUNTS_FOUND.getMessage());
        }
    }

    @GetMapping(value="/accounts/balance/{mobileNo}")
    public ResponseEntity<Double> checkBalance(@PathVariable Long  mobileNo, @RequestParam Long  accountNo) throws InfyMeMobileException {
        Double balance= bankAccountDetailsService.checkBalance(mobileNo,accountNo);
        if(balance!=null) {
            return new ResponseEntity<>(balance, HttpStatus.OK);
        }else{
            throw new InfyMeMobileException(HttpStatus.OK.toString(),NO_ACCOUNT_IS_LINKED.toString());
        }
    }

    @GetMapping(value="/accounts/statement/{mobileNo}")
    public ResponseEntity<List<TransactionDTO>> accountStatement( Long mobileNo) throws InfyMeMobileException {
        List<TransactionDTO> result= bankAccountDetailsService.accountStatement(mobileNo);
        if(result.size()!=0) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }else{
            throw new InfyMeMobileException(HttpStatus.OK.toString(),NO_ACTIVE_TRANSACTIONS.toString());
        }
    }

    @PutMapping(value="/accounts/fundtransfer")
    public ResponseEntity<String> fundTransfer(@RequestBody TransactionDTO transactionDTO) throws InfyMeMobileException {
        bankAccountDetailsService.fundTransfer(transactionDTO);
        return new ResponseEntity<>("SUCCESSFULLY SAVED",HttpStatus.OK);
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
