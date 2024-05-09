package com.bankSystem.account.converter;

import com.bankSystem.account.dto.AccountRequest;
import com.bankSystem.account.dto.AccountResponse;
import com.bankSystem.account.entity.AccountEntity;

import java.util.List;

public class AccountConverter {

    public static AccountEntity toEntity(AccountRequest request){
        AccountEntity entity = new AccountEntity();
        entity.setUser_name(request.getUser_name());
        entity.setBalance(request.getBalance());
        return entity;
    }
    public static AccountResponse toResponse(AccountEntity entity){
        AccountResponse response = new AccountResponse();
        response.setId(entity.getId());
        response.setUser_name(entity.getUser_name());
        response.setBalance(entity.getBalance());
        if(entity.getBankEntity() != null){
        response.setBankId(entity.getBankEntity().getId());
        response.setBankName(entity.getBankEntity().getName());
        }
        return response;
    }

    public static List<AccountResponse> toResponse(List<AccountEntity> entities){
        return entities.stream().map(AccountConverter::toResponse).toList();
    }
}
