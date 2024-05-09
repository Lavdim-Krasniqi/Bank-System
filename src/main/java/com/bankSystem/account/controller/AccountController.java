package com.bankSystem.account.controller;

import com.bankSystem.account.dto.AccountRequest;
import com.bankSystem.account.dto.AccountResponse;
import com.bankSystem.account.dto.TransferRequest;
import com.bankSystem.account.dto.WithdrawDepositRequest;
import com.bankSystem.account.service.AccountService;
import com.bankSystem.transaction.dto.TransactionResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/account")
public class AccountController {

    private AccountService accountService;


    @PostMapping
    public ResponseEntity<Void> createAccount(@RequestBody AccountRequest request) {
         accountService.createAccount(request);
         return ResponseEntity.ok().build();
    }

    @PutMapping("/deposit")
    public ResponseEntity<Void> deposit(@RequestBody WithdrawDepositRequest request) {
        accountService.deposit(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/withdrawal")
    public ResponseEntity<Void> withdrawal(@RequestBody WithdrawDepositRequest request) {
        accountService.withdrawal(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/transfer")
    public ResponseEntity<Void> transfer(@RequestBody TransferRequest transferRequest) {
        accountService.transfer(transferRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/findById/{id}")
    public AccountResponse findAccountById(@PathVariable Integer id) {
        return accountService.findAccountById(id);
    }

    @GetMapping
    public ResponseEntity<List<AccountResponse>> findAllAccounts() {
        return ResponseEntity.ok(accountService.findAllAccounts());
    }

    @GetMapping("/findAllAccountsByBankId/{bankId}")
    public ResponseEntity<List<AccountResponse>> findAllAccountsByBankId(@PathVariable Integer bankId) {
        return ResponseEntity.ok(accountService.findAllAccountsByBankId(bankId));
    }

    @GetMapping("/getAllTransactionByAccountId/{accountId}")
    public ResponseEntity<List<TransactionResponse>> getTransactionsByAccountId(@PathVariable Integer accountId) {
        return ResponseEntity.ok(accountService.getTransactionsByAccountId(accountId));
    }

    @GetMapping("/checkAccountBalance/{accountId}")
    public ResponseEntity<Double> checkAccountBalance(@PathVariable Integer accountId) {
        return ResponseEntity.ok(accountService.checkAccountBalance(accountId));
    }

}
