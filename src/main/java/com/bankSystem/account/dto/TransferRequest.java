package com.bankSystem.account.dto;

import lombok.Data;

@Data
public class TransferRequest {

    private double amount;
    private Integer originatingAccountId;
    private Integer resultingAccountId;
    private String reason;
}
