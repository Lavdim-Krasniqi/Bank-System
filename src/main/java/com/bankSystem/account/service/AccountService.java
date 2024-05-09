package com.bankSystem.account.service;

import com.bankSystem.account.dto.AccountRequest;
import com.bankSystem.account.dto.AccountResponse;
import com.bankSystem.account.dto.TransferRequest;
import com.bankSystem.account.dto.WithdrawDepositRequest;
import com.bankSystem.transaction.dto.TransactionResponse;

import java.util.List;

public interface AccountService {

    void createAccount(AccountRequest request);
    void deposit(WithdrawDepositRequest request);
    void withdrawal(WithdrawDepositRequest request);
    void transfer(TransferRequest request);
    AccountResponse findAccountById(Integer id);
    List<AccountResponse> findAllAccounts();
    List<AccountResponse> findAllAccountsByBankId(Integer bankId);
    List<TransactionResponse> getTransactionsByAccountId(Integer accountId);
    Double checkAccountBalance(Integer accountId);
}
