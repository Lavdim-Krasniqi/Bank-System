package com.bankSystem.bank.converter;

import com.bankSystem.bank.dto.BankRequest;
import com.bankSystem.bank.dto.BankResponse;
import com.bankSystem.bank.entity.BankEntity;

import java.util.List;

public class BankConverter {

    public static BankEntity toEntity(BankRequest bankRequest) {

        BankEntity toReturn = new BankEntity();
        toReturn.setName(bankRequest.getName());
        toReturn.setTotalTransferAmount(bankRequest.getTotalTransferAmount());
        toReturn.setTotalTransactionFeeAmount(bankRequest.getTotalTransactionFeeAmount());
        toReturn.setTransactionFlatFeeAmount(bankRequest.getTransactionFlatFeeAmount());
        toReturn.setTransactionPercentFeeValue(bankRequest.getTransactionPercentFeeValue());

        return toReturn;
    }

    public static BankResponse toResponse(BankEntity bankEntity) {
        if (bankEntity == null) { return null;}
        BankResponse toReturn = new BankResponse();
        toReturn.setBankId(bankEntity.getId());
        toReturn.setName(bankEntity.getName());
        toReturn.setTotalTransferAmount(bankEntity.getTotalTransferAmount());
        toReturn.setTotalTransactionFeeAmount(bankEntity.getTotalTransactionFeeAmount());
        toReturn.setTransactionFlatFeeAmount(bankEntity.getTransactionFlatFeeAmount());
        toReturn.setTransactionPercentFeeValue(bankEntity.getTransactionPercentFeeValue());
        return toReturn;
    }

    public static List<BankResponse> toResponse(List<BankEntity> bankEntities) {
        return bankEntities.stream().map(BankConverter::toResponse).toList();
    }
}
