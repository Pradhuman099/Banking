package com.infy.banking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction",schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(name="receiver_account_number")
    Long receiverAccountNumber;

    @Column(name="amount")
    Double amount;

    @Column(name="transaction_date_time")
    Timestamp transactionDateTime;

    @Column(name="remarks")
    String remarks;

    @Column(name="paid_from")
    Long paidFrom;

    @Column(name="sender_account_number")
    Long senderAccountNumber;

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public String getModeOfTransaction() {
        return modeOfTransaction;
    }

    public void setModeOfTransaction(String modeOfTransaction) {
        this.modeOfTransaction = modeOfTransaction;
    }

    public Long getPaidTo() {
        return paidTo;
    }

    public void setPaidTo(Long paidTo) {
        this.paidTo = paidTo;
    }

    public Long getReceiverAccountNumber() {
        return receiverAccountNumber;
    }

    public void setReceiverAccountNumber(Long receiverAccountNumber) {
        this.receiverAccountNumber = receiverAccountNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Timestamp getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(Timestamp transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Long getPaidFrom() {
        return paidFrom;
    }

    public void setPaidFrom(Long paidFrom) {
        this.paidFrom = paidFrom;
    }

    public Long getSenderAccountNumber() {
        return senderAccountNumber;
    }

    public void setSenderAccountNumber(Long senderAccountNumber) {
        this.senderAccountNumber = senderAccountNumber;
    }
}
