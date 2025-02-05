package com.infy.banking.repository;

import com.infy.banking.entity.DigitalBankAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DigitalBankAccountRepo  extends JpaRepository<DigitalBankAccountEntity,Integer> {

    @Query(value = "Select digital_banking_id from public.digital_bank_account order by digital_banking_id desc limit 1",nativeQuery = true)
    public String findLastEntryId();

    @Query(value = "Select digital_banking_id from public.digital_bank_account where account_number=?1 and mobile_number=?2",nativeQuery = true)
    public String findExists(Long acc, Long num);
}

