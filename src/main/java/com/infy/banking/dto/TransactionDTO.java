package com.infy.banking.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionDTO {
    Integer transactionId;
    String modeOfTransaction;
    Long paidTo;
    Long receiverAccountNumber;
    Double amount;
    LocalDateTime transactionDateTime;
    String remarks;
    Long paidFrom;
    Long senderAccountNumber;
}
