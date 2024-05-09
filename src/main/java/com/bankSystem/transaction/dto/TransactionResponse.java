package com.bankSystem.transaction.dto;

import lombok.Data;

@Data
public class TransactionResponse {

    private Integer transactionId;
    private String reason;
    private String originatingAccountBankName;
    private Integer originatingAccountId;
    private String originatingUserAccountName;
    private Integer resultingAccountId;
    private String resultingUserAccountName;
    private String resultingAccountBankName;
}
