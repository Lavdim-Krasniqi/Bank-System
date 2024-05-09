package com.bankSystem.bank.entity;

import com.bankSystem.account.entity.AccountEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bank")
@Getter
@Setter
public class BankEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String name;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bankEntity")
    private List<AccountEntity> accountEntities = new ArrayList<>();
    private Double totalTransactionFeeAmount;
    private Double totalTransferAmount;
    private Double transactionFlatFeeAmount;
    private Integer transactionPercentFeeValue;

}
