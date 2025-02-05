package com.infy.banking.serviceImpl;

import com.infy.banking.dto.BankAccountDTO;
import com.infy.banking.dto.TransactionDTO;
import com.infy.banking.entity.BankAccountEntity;
import com.infy.banking.entity.DigitalBankAccountEntity;
import com.infy.banking.entity.TransactionEntity;
import com.infy.banking.dto.LinkingAcountDTO;
import com.infy.banking.entity.BankAccountEntity;
import com.infy.banking.entity.DigitalBankAccountEntity;
import com.infy.banking.enums.ExceptionConstants;
import com.infy.banking.exceptions.InfyMeMobileException;
import com.infy.banking.repository.BankAccountRepo;
import com.infy.banking.repository.DigitalBankAccountRepo;
import com.infy.banking.repository.TransactionRepo;
import com.infy.banking.service.BankAccountDetailsService;
import com.infy.banking.utility.OTPUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DisplayBankAccountDetailsImpl implements BankAccountDetailsService {

    @Autowired
    private BankAccountRepo bankAccountRepo;

    @Autowired
    private DigitalBankAccountRepo digitalBankAccountRepo;

    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired
    OTPUtility utility;

    @Override
    public List<BankAccountDTO> listBankAccountDetailsViaMobileNo(Long mobileNo){
            return bankAccountRepo.findAllByMobile(mobileNo)
                    .parallelStream()
                    .map(e -> new BankAccountDTO(e.getAccountNumber(), e.getBankName(), e.getBalance(), e.getAccountType(), e.getIfscCode(), e.getOpeningDate(), e.getMobileNumber()))
                    .collect(Collectors.toList());
    }

    @Override
    public Double checkBalance(Long mobileNo, Long accountNo){
        return digitalBankAccountRepo.getBalanceViaLinkedMobileNo(mobileNo,accountNo);
    }

    @Override
    public List<TransactionDTO> accountStatement(Long mobileNo){
        return transactionRepo.findTransactionsByMobileNumber(mobileNo);
    }

    @Override
    public String fundTransfer(TransactionDTO transactionDTO) throws InfyMeMobileException {
        try{
        TransactionEntity transaction= new TransactionEntity();
        List<BankAccountEntity> lstBank= bankAccountRepo.findAllData();
        DigitalBankAccountEntity digitalBankAccountEntity= new DigitalBankAccountEntity();
        List<DigitalBankAccountEntity> lstDigital= digitalBankAccountRepo.findAllData();
        if(checkAccount(lstBank,transactionDTO.getSenderAccountNumber())==true) {
            transaction.setSenderAccountNumber(transactionDTO.getSenderAccountNumber());
        }else {
            throw new InfyMeMobileException(HttpStatus.OK.toString(),ExceptionConstants.USER_NOT_FOUND.getMessage());
        }
        transaction.setModeOfTransaction(transactionDTO.getModeOfTransaction());
        if(checkAccount(lstBank,transactionDTO.getReceiverAccountNumber())==true) {
            transaction.setReceiverAccountNumber(transactionDTO.getReceiverAccountNumber());
        }else {
            throw new InfyMeMobileException(HttpStatus.OK.toString(),ExceptionConstants.USER_NOT_FOUND.getMessage());
        }
        transaction.setTransactionDateTime(Timestamp.valueOf(LocalDateTime.now()));
        transaction.setRemarks(transactionDTO.getRemarks());
        if(isItEqual(lstDigital,transactionDTO)==true) {
            transaction.setPaidFrom(transactionDTO.getPaidFrom());
        }else {
            throw new InfyMeMobileException(HttpStatus.OK.toString(),ExceptionConstants.USERID_NOT_FOUND.getMessage());
        }
        if(isItEqualReciever(lstDigital,transactionDTO)==true){
            transaction.setPaidTo(transactionDTO.getPaidTo());
        }else {
            throw new InfyMeMobileException(HttpStatus.OK.toString(),ExceptionConstants.USERID_NOT_FOUND.getMessage());
        }
        if(transactionDTO.getAmount()<bankAccountRepo.amountData(transactionDTO.getSenderAccountNumber())){
            transaction.setAmount(transactionDTO.getAmount());
            BankAccountEntity bankAccountEntitySender= bankAccountRepo.findById(transactionDTO.getSenderAccountNumber()).orElseThrow(()->new RuntimeException("Account Not Found"));
            BankAccountEntity bankAccountEntityReceiver= bankAccountRepo.findById(transactionDTO.getReceiverAccountNumber()).orElseThrow(()->new RuntimeException("Account Not Found"));
            bankAccountEntitySender.setBalance(bankAccountEntitySender.getBalance()-transactionDTO.getAmount());
            bankAccountEntityReceiver.setBalance(bankAccountEntityReceiver.getBalance()+transactionDTO.getAmount());
            bankAccountRepo.save(bankAccountEntitySender);
            bankAccountRepo.save(bankAccountEntityReceiver);
        }
        else {
            throw new InfyMeMobileException(HttpStatus.OK.toString(),ExceptionConstants.INSUFFICIENT_FUNDS.getMessage());
        }
        transactionRepo.save(transaction);
        return "SUCCESSFULLY SAVED";
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public Boolean isItEqual(List<DigitalBankAccountEntity> digitalBankAccountEntities, TransactionDTO transactionDTO){
        for(DigitalBankAccountEntity entities: digitalBankAccountEntities){
            if(entities.getAccountNumber().equals(transactionDTO.getSenderAccountNumber()) && entities.getMobileNumber().equals(transactionDTO.getPaidFrom())){
                return true;
            }
        }
        return false;
    }

    public Boolean isItEqualReciever(List<DigitalBankAccountEntity> digitalBankAccountEntities, TransactionDTO transactionDTO){
        for(DigitalBankAccountEntity entities: digitalBankAccountEntities){
            if(entities.getAccountNumber().equals(transactionDTO.getReceiverAccountNumber()) && entities.getMobileNumber().equals(transactionDTO.getPaidTo())){
                return true;
            }
        }
        return false;
    }

    public Boolean checkAccount(List<BankAccountEntity> bankAccountEntities,Long account) {
        for (BankAccountEntity entity : bankAccountEntities) {
            System.out.println(entity.getAccountNumber());
            System.out.println(account);
            if (entity.getAccountNumber().equals(account)) {
                return true;
            }
        }
        return false;
    }


//    @Override
//    public List<BankAccountDTO> listBankAccountDetailsViaMobileNo(Long mobileNo) {
//        return bankAccountRepo.findAllByMobile(mobileNo)
//                .parallelStream()
//                .map(e -> new BankAccountDTO(e.getAccountNumber(), e.getBankName(), e.getBalance(), e.getAccountType(), e.getIfscCode(), e.getOpeningDate(), e.getMobileNumber()))
//                .collect(Collectors.toList());
//    }

    @Override
    public String createAccount(BankAccountDTO accountDTO) throws MethodArgumentNotValidException {
        BankAccountEntity bankAccount = new BankAccountEntity();
        bankAccount.setAccountType(accountDTO.getAccountType());
        bankAccount.setBankName(accountDTO.getBankName());
        bankAccount.setBalance(accountDTO.getBalance());
        bankAccount.setIfscCode(accountDTO.getIfscCode());
        bankAccount.setMobileNumber(accountDTO.getMobileNumber());

        bankAccount.setOpeningDate(Date.valueOf(LocalDate.now()));

        List<BankAccountDTO> accounts = bankAccountRepo.findAllByMobile(accountDTO.getMobileNumber());
        List<FieldError> fieldErrors = new ArrayList<>();
        fieldErrors.add(new FieldError("account", "account type", "same number and account name already exist"));
        for (BankAccountDTO account : accounts) {
            if (account.getAccountType().equals(accountDTO.getAccountType())) {
                BindingResult bindingResult = new BeanPropertyBindingResult(null, "account");
                bindingResult.addError(fieldErrors.get(0));
                throw new MethodArgumentNotValidException(null, bindingResult);
            }
        }

        BankAccountEntity ba = bankAccountRepo.save(bankAccount);

        return ba.getAccountNumber().toString();
    }

    public String linkAccount(LinkingAcountDTO accountDTO, Long mobileNumber) throws InfyMeMobileException {
        List<BankAccountDTO> accounts = bankAccountRepo.findAllByMobile(mobileNumber);
        if (accounts.size() == 0) {
            throw new InfyMeMobileException(HttpStatus.OK.toString(), ExceptionConstants.NO_ACCOUNTS_FOUND.getMessage());
        }
        String ifExists = digitalBankAccountRepo.findExists(accountDTO.getAccountNumber(), mobileNumber);

        if (ifExists != null && ifExists.length() != 0 ) {
            throw new InfyMeMobileException(HttpStatus.OK.toString(), ExceptionConstants.ACCOUNT_IS_LINKED.getMessage());

        }

        for (BankAccountDTO account : accounts) {
            if (account.getAccountNumber().equals(accountDTO.getAccountNumber())) {
                DigitalBankAccountEntity digitalBankAccount = new DigitalBankAccountEntity();
                digitalBankAccount.setAccountNumber(account.getAccountNumber());
                digitalBankAccount.setMobileNumber(mobileNumber);
                digitalBankAccount.setAccountType(account.getAccountType());

                String lstEntry = digitalBankAccountRepo.findLastEntryId();
                if (lstEntry == null || lstEntry.length() == 0) {
                    digitalBankAccount.setDigitalBankingId("w_1000");
                    digitalBankAccountRepo.save(digitalBankAccount);
                } else {
                    Integer num = Integer.valueOf(lstEntry.split("_")[1]) + 1;
                    digitalBankAccount.setDigitalBankingId("w_" + num);
                    digitalBankAccountRepo.save(digitalBankAccount);

                }

            }
        }
        return "Account is linked now";
    }

    public String linkAccountWithOTP(LinkingAcountDTO accountDTO, Long mobileNumber) throws InfyMeMobileException {
        if (!accountDTO.getOtp().equals(utility.sendOTP())) {
            throw new InfyMeMobileException(HttpStatus.OK.toString(), ExceptionConstants.AUTHENTICATION_FAILED.getMessage());
        }
        String ifExists = digitalBankAccountRepo.findExists(accountDTO.getAccountNumber(), mobileNumber);
        if (ifExists != null && ifExists.length() != 0) {
            throw new InfyMeMobileException(HttpStatus.OK.toString(), ExceptionConstants.ACCOUNT_IS_LINKED.getMessage());

        }
        List<BankAccountDTO> accounts = bankAccountRepo.findAllByMobile(mobileNumber);
        if (accounts.size() == 0) {
            throw new InfyMeMobileException(HttpStatus.OK.toString(), ExceptionConstants.NO_ACCOUNTS_FOUND.getMessage());
        }

        for (BankAccountDTO account : accounts) {
            if (account.getAccountNumber().equals(accountDTO.getAccountNumber())) {
                DigitalBankAccountEntity digitalBankAccount = new DigitalBankAccountEntity();
                digitalBankAccount.setAccountNumber(account.getAccountNumber());
                digitalBankAccount.setMobileNumber(mobileNumber);
                digitalBankAccount.setAccountType(account.getAccountType());

                String lstEntry = digitalBankAccountRepo.findLastEntryId();
                if (lstEntry == null || lstEntry.length() == 0) {
                    digitalBankAccount.setDigitalBankingId("w_1000");
                    digitalBankAccountRepo.save(digitalBankAccount);
                } else {
                    Integer num = Integer.valueOf(lstEntry.split("_")[1]) + 1;
                    digitalBankAccount.setDigitalBankingId("w_" + num);
                    digitalBankAccountRepo.save(digitalBankAccount);

                }

            }
        }
        return "Account is linked now";
    }

}
