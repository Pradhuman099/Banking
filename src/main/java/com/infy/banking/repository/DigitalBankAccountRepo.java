package com.infy.banking.repository;

import com.infy.banking.entity.DigitalBankAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DigitalBankAccountRepo  extends JpaRepository<DigitalBankAccountEntity,Integer> {

}
