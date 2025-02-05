package com.infy.banking.dto;
import jakarta.validation.constraints.*;

        import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;



public class LinkingAcountDTO {

    @NotNull(message="Account Number Cannot be null")
    @Min(value=1000000, message="the minimum length of account number should be 7")
    Long accountNumber;

    Integer otp;

    public @NotNull(message = "Account Number Cannot be null") @Min(value = 1000000, message = "the minimum length of account number should be 7") Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(@NotNull(message = "Account Number Cannot be null") @Min(value = 1000000, message = "the minimum length of account number should be 7") Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Integer getOtp() {
        return otp;
    }

    public void setOtp(Integer otp) {
        this.otp = otp;
    }
}

