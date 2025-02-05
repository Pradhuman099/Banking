package com.infy.banking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "digital_bank_account",schema = "public")
@Data
public class DigitalBankAccountEntity {
    @Id
    @Column(name="digital_banking_id")
    String digitalBankingId;
    @Column(name="mobile_number")
    Long mobileNumber;

    @Column(name="account_number")
    Long accountNumber;

    @Column(name="account_type")
    String accountType;

    public String getDigitalBankingId() {
        return digitalBankingId;
    }

    public void setDigitalBankingId(String digitalBankingId) {
        this.digitalBankingId = digitalBankingId;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
