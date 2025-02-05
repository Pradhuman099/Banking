package com.infy.banking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "digital_bank_account",schema = "public")
@Data
public class DigitalBankAccountEntity {
    @Column(name="digital_banking_id")
    String digitalBankingId;
    @Column(name="mobile_number")
    Long mobileNumber;

    @Column(name="account_number")
    Long accountNumber;

    @Column(name="account_type")
    String accountType;
}
