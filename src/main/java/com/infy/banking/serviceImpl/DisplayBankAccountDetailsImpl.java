package com.infy.banking.serviceImpl;

import com.infy.banking.dto.BankAccountDTO;
import com.infy.banking.repository.BankAccountRepo;
import com.infy.banking.service.BankAccountDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DisplayBankAccountDetailsImpl implements BankAccountDetailsService {

    @Autowired
    private BankAccountRepo bankAccountRepo;

    @Override
    public List<BankAccountDTO> listBankAccountDetailsViaMobileNo(Long mobileNo){
        return bankAccountRepo.findAll(mobileNo)
                .parallelStream()
                .map(e-> new BankAccountDTO(e.getAccountNumber(),e.getBankName(),e.getBalance(),e.getAccountType(),e.getIfscCode(),e.getOpeningDate(),e.getMobileNumber()))
                .collect(Collectors.toList());
    }
}
