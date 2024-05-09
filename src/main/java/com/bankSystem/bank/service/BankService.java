package com.bankSystem.bank.service;

import com.bankSystem.bank.dto.BankRequest;
import com.bankSystem.bank.dto.BankResponse;
import com.bankSystem.bank.entity.BankEntity;
import com.bankSystem.transaction.dto.TransactionResponse;

import java.util.List;

public interface BankService {

    void addBank(BankRequest request);

    BankResponse findById(Integer id);

    List<BankResponse> findAll();

    Double getTotalTransferAmount(Integer bankId);

    Double getTotalTransactionFeeAmount(Integer bankId);

    BankEntity updateBank(BankEntity entity);

    BankEntity findBankEntityById(Integer id);
}
