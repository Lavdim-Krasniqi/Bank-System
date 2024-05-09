package com.bankSystem.transaction.entity;

import com.bankSystem.account.entity.AccountEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "transaction")
@Getter
@Setter
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double amount;
    private String reason;
    @ManyToOne
    @JoinColumn(name = "originating_account_id")
    private AccountEntity originating;
    @ManyToOne
    @JoinColumn(name = "resulting_account_id")
    private AccountEntity resulting;
}
