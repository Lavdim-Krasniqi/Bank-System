package com.bankSystem.bank.dto;

import lombok.Data;

@Data
public class BankRequest {

    private String name;
    private Double totalTransactionFeeAmount;
    private Double totalTransferAmount;
    private Double transactionFlatFeeAmount;
    private Integer transactionPercentFeeValue;
}
