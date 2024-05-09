package com.bankSystem.account.repository;

import com.bankSystem.account.entity.AccountEntity;

import java.util.List;

public interface AccountRepository {

    void addAccount(AccountEntity entity);
    void updateAccount(AccountEntity entity);
    AccountEntity findAccountById(Integer id);
    List<AccountEntity> findAllAccounts();
    List<AccountEntity> findAllAccountsByBankId(Integer bankId);
    Double getBalance(Integer accountId);
}
