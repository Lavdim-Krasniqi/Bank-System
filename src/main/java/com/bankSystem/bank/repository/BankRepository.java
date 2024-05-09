package com.bankSystem.bank.repository;

import com.bankSystem.bank.entity.BankEntity;

import java.util.List;

public interface BankRepository {

    void addBank(BankEntity entity);

    BankEntity updateBank(BankEntity entity);

    BankEntity findById(Integer id);

    List<BankEntity> findAll();

    Double getTotalTransferAmount(Integer bankId);

    Double getTotalTransactionFeeAmount(Integer bankId);
}
