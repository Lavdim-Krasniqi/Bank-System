package com.bankSystem.bank.controller;

import com.bankSystem.bank.dto.BankRequest;
import com.bankSystem.bank.dto.BankResponse;
import com.bankSystem.bank.service.BankService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank")
@AllArgsConstructor
public class BankController {

    private final BankService bankService;

    @PostMapping
    public void addBank(@RequestBody BankRequest request) {
        bankService.addBank(request);
    }
    @GetMapping("/findById/{bankId}")
    public BankResponse findById(@PathVariable Integer bankId) {
        return bankService.findById(bankId);
    }
    @GetMapping
    public List<BankResponse> findAll() {
        return bankService.findAll();
    }

    @GetMapping("/getTotalTransferAmount/{bankId}")
    public Double getTotalTransferAmount(@PathVariable Integer bankId) {
        return bankService.getTotalTransferAmount(bankId);
    }

    @GetMapping("/getTotalTransactionFeeAmount/{bankId}")
    public Double getTotalTransactionFeeAmount(@PathVariable Integer bankId) {
        return bankService.getTotalTransactionFeeAmount(bankId);
    }
}
