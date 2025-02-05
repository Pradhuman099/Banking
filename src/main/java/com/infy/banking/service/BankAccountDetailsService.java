package com.infy.banking.service;

import com.infy.banking.dto.BankAccountDTO;

import java.util.List;

public interface BankAccountDetailsService {
    public List<BankAccountDTO> listBankAccountDetailsViaMobileNo(Long mobileNo);
}
