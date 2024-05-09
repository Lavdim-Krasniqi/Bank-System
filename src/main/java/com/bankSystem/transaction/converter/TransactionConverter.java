package com.bankSystem.transaction.converter;

import com.bankSystem.transaction.dto.TransactionResponse;
import com.bankSystem.transaction.entity.TransactionEntity;

import java.util.List;

public class TransactionConverter {

    public static TransactionResponse toDto(TransactionEntity transaction) {
        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setTransactionId(transaction.getId());
        transactionResponse.setReason(transaction.getReason());
        transactionResponse.setAmount(transaction.getAmount());
        transactionResponse.setOriginatingAccountId(transaction.getOriginating().getId());
        transactionResponse.setOriginatingUserAccountName(transaction.getOriginating().getUser_name());
        transactionResponse.setOriginatingAccountBankName(transaction.getOriginating().getBankEntity().getName());

        transactionResponse.setResultingAccountId(transaction.getResulting().getId());
        transactionResponse.setResultingUserAccountName(transaction.getResulting().getUser_name());
        transactionResponse.setResultingAccountBankName(transaction.getResulting().getBankEntity().getName());

        return transactionResponse;
    }

    public static List<TransactionResponse> toDto(List<TransactionEntity> transactions) {
        return transactions.stream().map(TransactionConverter::toDto).toList();
    }
}
