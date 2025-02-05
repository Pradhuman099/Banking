package com.infy.banking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "transaction",schema = "public")
@Data
public class TransactionEntity {

    @Id
    @SequenceGenerator(name = "TRANSACTION_ID_GENERATOR", sequenceName = "transaction_seq", allocationSize = 1, schema = "public")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRANSACTION_ID_GENERATOR")
    @Column(name="transaction_id")
    Integer transactionId;

    @Column(name="mode_of_transaction")
    String modeOfTransaction;

    @Column(name="paid_to")
    Long paidTo;

    @Column(name="receiver_account-number")
    Long receiverAccountNumber;

    @Column(name="amount")
    Double amount;

    @Column(name="transaction_date_time")
    LocalDateTime transactionDateTime;

    @Column(name="remarks")
    String remarks;

    @Column(name="paid_from")
    Long paidFrom;

    @Column(name="sender_account_number")
    Long senderAccountNumber;
}
