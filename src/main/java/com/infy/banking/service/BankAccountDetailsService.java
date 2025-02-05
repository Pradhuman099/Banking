package com.infy.banking.service;

import com.infy.banking.dto.BankAccountDTO;
import com.infy.banking.dto.TransactionDTO;
import com.infy.banking.exceptions.InfyMeMobileException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BankAccountDetailsService {
    public List<BankAccountDTO> listBankAccountDetailsViaMobileNo(Long mobileNo);

    public Double checkBalance(Long mobileNo, Long accountNo);

    public List<TransactionDTO> accountStatement(Long mobileNo);

    public String fundTransfer(TransactionDTO transactionDTO) throws InfyMeMobileException;
}