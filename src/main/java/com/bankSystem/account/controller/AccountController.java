package com.bankSystem.account.controller;

import com.bankSystem.account.dto.AccountRequest;
import com.bankSystem.account.dto.AccountResponse;
import com.bankSystem.account.dto.TransferRequest;
import com.bankSystem.account.dto.WithdrawDepositRequest;
import com.bankSystem.account.service.AccountService;
import com.bankSystem.transaction.dto.TransactionResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/account")
public class AccountController {

    private AccountService accountService;


    @PostMapping
    public void createAccount(@RequestBody AccountRequest request) {
        accountService.createAccount(request);
    }

    @PutMapping("/deposit")
    public void deposit(@RequestBody WithdrawDepositRequest request) {
        accountService.deposit(request);
    }

    @PutMapping("/withdrawal")
    public void withdrawal(@RequestBody WithdrawDepositRequest request) {
        accountService.withdrawal(request);
    }

    @PutMapping("/transfer")
    public void transfer(@RequestBody TransferRequest transferRequest) {
        accountService.transfer(transferRequest);
    }

    @GetMapping("/findById/{id}")
    public AccountResponse findAccountById(@PathVariable Integer id) {
        return accountService.findAccountById(id);
    }

    @GetMapping
    public List<AccountResponse> findAllAccounts() {
        return accountService.findAllAccounts();
    }

    @GetMapping("/findAllAccountsByBankId/{bankId}")
    public List<AccountResponse> findAllAccountsByBankId(@PathVariable Integer bankId) {
        return accountService.findAllAccountsByBankId(bankId);
    }

    @GetMapping("/getAllTransactionByAccountId/{accountId}")
    public List<TransactionResponse> getTransactionsByAccountId(@PathVariable Integer accountId) {
        return accountService.getTransactionsByAccountId(accountId);
    }

    @GetMapping("/checkAccountBalance/{accountId}")
    public Double checkAccountBalance(@PathVariable Integer accountId) {
        return accountService.checkAccountBalance(accountId);
    }

}
