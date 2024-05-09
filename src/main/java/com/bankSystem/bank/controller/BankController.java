package com.bankSystem.bank.controller;

import com.bankSystem.bank.dto.BankRequest;
import com.bankSystem.bank.dto.BankResponse;
import com.bankSystem.bank.service.BankService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank")
@AllArgsConstructor
public class BankController {

    private final BankService bankService;

    @PostMapping
    public ResponseEntity<Void> addBank(@RequestBody BankRequest request) {
        bankService.addBank(request);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/findById/{bankId}")
    public ResponseEntity<BankResponse> findById(@PathVariable Integer bankId) {
        return ResponseEntity.ok(bankService.findById(bankId));
    }
    @GetMapping
    public ResponseEntity<List<BankResponse>> findAll() {
        return ResponseEntity.ok(bankService.findAll());
    }

    @GetMapping("/getTotalTransferAmount/{bankId}")
    public ResponseEntity<Double> getTotalTransferAmount(@PathVariable Integer bankId) {
        return ResponseEntity.ok(bankService.getTotalTransferAmount(bankId));
    }

    @GetMapping("/getTotalTransactionFeeAmount/{bankId}")
    public ResponseEntity<Double> getTotalTransactionFeeAmount(@PathVariable Integer bankId) {
        return ResponseEntity.ok(bankService.getTotalTransactionFeeAmount(bankId));
    }
}
