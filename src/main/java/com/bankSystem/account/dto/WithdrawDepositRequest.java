package com.bankSystem.account.dto;

import lombok.Data;

@Data
public class WithdrawDepositRequest {

    private Integer accountId;
    private Double amount;
    private String reason;
}
