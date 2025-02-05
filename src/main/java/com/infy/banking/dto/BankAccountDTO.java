package com.infy.banking.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;


@NoArgsConstructor
public class BankAccountDTO {

    @NotNull(message="Cannot be null")
    @Min(value=1000000, message="the minimum length should be 7")
    Long accountNumber;

    @NotNull(message="Cannot be null")
    @Size(min=5, max=15, message="the minimum length should be 5, and the maximum length should be 15")
    String bankName;

    @Min(value=0,message="The minimum value must be 0. Can't be a negative value.")
    Double balance;

    @NotNull(message="Cannot be null")
    @Size(min=1, max=10, message="the minimum length should be 1, and the maximum length should be 10")
    String accountType;

    @NotNull(message="Cannot be null")
    @Size(min=1, max=15, message="the minimum length should be 1, and the maximum length should be 15")
    String ifscCode;

    @Past(message="Must be a past Date")
    Date openingDate;

    @NotNull(message="Cannot be null")
    @Size(min=10, max=10, message="the minimum length should be 10, and the maximum length should be 10")
    Long mobileNumber;

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public Date getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(Date openingDate) {
        this.openingDate = openingDate;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public BankAccountDTO(Long accountNumber, String bankName, Double balance, String accountType, String ifscCode, Date openingDate, Long mobileNumber) {
        this.accountNumber = accountNumber;
        this.bankName = bankName;
        this.balance = balance;
        this.accountType = accountType;
        this.ifscCode = ifscCode;
        this.openingDate = openingDate;
        this.mobileNumber = mobileNumber;
    }
}
