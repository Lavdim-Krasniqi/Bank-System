package com.bankSystem.transaction.service;

import com.bankSystem.transaction.dto.TransactionResponse;
import com.bankSystem.transaction.entity.TransactionEntity;

import java.util.List;

public interface TransactionService {

    void addTransaction(TransactionEntity transaction);
    List<TransactionResponse> getTransactionByAccountId(Integer accountId);
}
