package com.infy.banking.repository;

import com.infy.banking.dto.BankAccountDTO;
import com.infy.banking.entity.BankAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountRepo  extends JpaRepository<BankAccountEntity,Long> {

    @Query(value="select * from public.bank_account where mobile_number=?1",nativeQuery = true)
    List<BankAccountDTO> findAllByMobile(Long mobileNo);

    @Query(value = "select * from public.bank_account",nativeQuery = true)
    List<BankAccountEntity> findAllData();

    @Query(value = "select balance from public.bank_account where account_number=?1",nativeQuery = true)
    Double amountData(Long senderAccountNumber);
}
