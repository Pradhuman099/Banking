package com.infy.banking.repository;

import com.infy.banking.dto.BankAccountDTO;
import com.infy.banking.entity.BankAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountRepo  extends JpaRepository<BankAccountEntity,Integer> {

    List<BankAccountDTO> findAll(Long mobileNo);
}
