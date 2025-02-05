package com.infy.banking.repository;

import com.infy.banking.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepo  extends JpaRepository<TransactionEntity,Integer> {

}
