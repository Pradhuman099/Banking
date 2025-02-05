package com.infy.banking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "bank_account",schema = "public")
@Data
public class BankAccountEntity {
    @Id
    @SequenceGenerator(name = "BANK_ACCOUNT_ID_GENERATOR", sequenceName = "bank_account_seq", allocationSize = 1, schema = "public")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BANK_ACCOUNT_GENERATOR")
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
    LocalDate openingDate;

    @Column(name="mobile_number")
    Long mobileNumber;
}
