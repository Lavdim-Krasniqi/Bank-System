package com.bankSystem.transaction.service;

import com.bankSystem.transaction.converter.TransactionConverter;
import com.bankSystem.transaction.dto.TransactionResponse;
import com.bankSystem.transaction.entity.TransactionEntity;
import com.bankSystem.transaction.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public void addTransaction(TransactionEntity transaction) {
        transactionRepository.addTransaction(transaction);
    }

    @Override
    public List<TransactionResponse> getTransactionByAccountId(Integer accountId) {
        return TransactionConverter.toDto(transactionRepository.getTransactionsByAccountId(accountId));
    }
}
