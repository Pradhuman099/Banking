package com.infy.banking.repository;

import com.infy.banking.dto.TransactionDTO;
import com.infy.banking.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepo  extends JpaRepository<TransactionEntity,Integer> {

    @Query(value = "select * from public.transaction where mobile_number=9999999999",nativeQuery = true)
    List<TransactionDTO> findTransactionsByMobileNumber(Long mobileNo);
}
