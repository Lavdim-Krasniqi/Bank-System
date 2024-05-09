package com.bankSystem.bank.dto;

import lombok.Data;

@Data
public class BankResponse {

    private Integer bankId;
    private String name;
    private Double totalTransactionFeeAmount;
    private Double totalTransferAmount;
    private Double transactionFlatFeeAmount;
    private Integer transactionPercentFeeValue;
}
