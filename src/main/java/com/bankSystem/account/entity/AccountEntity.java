package com.bankSystem.account.entity;

import com.bankSystem.bank.entity.BankEntity;
import com.bankSystem.transaction.entity.TransactionEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "account")
@Getter
@Setter
public class AccountEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String user_name;
    private Double balance;
    @ManyToOne
    @JoinColumn(name = "bank_id")
    private BankEntity bankEntity;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "originating")
    private List<TransactionEntity> originatingTransactions = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "resulting")
    private List<TransactionEntity> resultingTransaction = new ArrayList<>();

}
