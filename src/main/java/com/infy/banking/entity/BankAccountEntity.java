package com.infy.banking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "bank_account",schema = "public")
@Getter
@Setter
public class BankAccountEntity {
    @Id
    @SequenceGenerator(name = "BANK_ACCOUNT_ID_GENERATOR", sequenceName = "bank_account_seq", allocationSize = 1, schema = "public")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BANK_ACCOUNT_ID_GENERATOR")
    @Column(name="account_number")
    Long accountNumber;

    @Column(name="bank_name")
    String bankName;

    @Column(name="balance")
    Double balance;

    @Column(name="account_type")
    String accountType;

    @Column(name="ifsc_code")
    String ifscCode;

    @Column(name="opening_date")
    Date openingDate;

    @Column(name="mobile_number")
    Long mobileNumber;

    public Long getAccountNumber() {
        return accountNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public Double getBalance() {
        return balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public Date getOpeningDate() {
        return openingDate;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public void setOpeningDate(Date openingDate) {
        this.openingDate = openingDate;
    }

    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
