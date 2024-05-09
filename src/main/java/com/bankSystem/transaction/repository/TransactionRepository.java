package com.bankSystem.transaction.repository;

import com.bankSystem.transaction.entity.TransactionEntity;

import java.util.List;

public interface TransactionRepository {

    void addTransaction(TransactionEntity transaction);
    List<TransactionEntity> getTransactionsByAccountId(Integer accountId);

}
