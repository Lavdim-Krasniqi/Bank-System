package com.bankSystem.account.dto;

import lombok.Data;

@Data
public class AccountResponse {
    private int id;
    private String user_name;
    private Double balance;
    private String bankName;
    private Integer bankId;
}
