package com.infy.banking.service;

import com.infy.banking.dto.BankAccountDTO;
import com.infy.banking.dto.TransactionDTO;
import com.infy.banking.dto.LinkingAcountDTO;
import com.infy.banking.exceptions.InfyMeMobileException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Component
public interface BankAccountDetailsService {
    public List<BankAccountDTO> listBankAccountDetailsViaMobileNo(Long mobileNo);

    public Double checkBalance(Long mobileNo, Long accountNo);

    public List<TransactionDTO> accountStatement(Long mobileNo);

    public String fundTransfer(TransactionDTO transactionDTO) throws InfyMeMobileException;

    public String createAccount(BankAccountDTO accountDTO) throws MethodArgumentNotValidException;

    public String linkAccount(LinkingAcountDTO accountDTO,Long mobileNumber) throws InfyMeMobileException;

    public String linkAccountWithOTP(LinkingAcountDTO accountDTO,Long mobileNumber) throws InfyMeMobileException;

}
