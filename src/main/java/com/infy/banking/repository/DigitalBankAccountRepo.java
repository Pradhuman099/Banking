package com.infy.banking.repository;

import com.infy.banking.entity.BankAccountEntity;
import com.infy.banking.entity.DigitalBankAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DigitalBankAccountRepo  extends JpaRepository<DigitalBankAccountEntity,Integer> {
    @Query(value="select ba.balance from public.bank_account ba inner join public.digital_bank_account dba\n" +
            "on ba.account_number=dba.account_number where dba.mobile_number=?1 and dba.account_number=?2",nativeQuery = true)
    Double getBalanceViaLinkedMobileNo(Long mobileNo,Long accountNo);

    @Query(value = "select * from public.digital_bank_account",nativeQuery = true)
    List<DigitalBankAccountEntity> findAllData();
    @Query(value = "Select digital_banking_id from public.digital_bank_account order by digital_banking_id desc limit 1",nativeQuery = true)
    public String findLastEntryId();

    @Query(value = "Select digital_banking_id from public.digital_bank_account where account_number=?1 and mobile_number=?2",nativeQuery = true)
    public String findExists(Long acc, Long num);
}

