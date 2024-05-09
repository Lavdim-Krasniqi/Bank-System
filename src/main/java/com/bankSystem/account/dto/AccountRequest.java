package com.bankSystem.account.dto;

import lombok.Data;

@Data
public class AccountRequest {

    private String user_name;
    private Double balance;
    private Integer bankId;
}
