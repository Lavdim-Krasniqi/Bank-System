package com.bankSystem.transaction.repository;

import com.bankSystem.transaction.entity.TransactionEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class TransactionRepositoryImpl implements TransactionRepository {

    private final EntityManager entityManager;

    @Override
    public void addTransaction(TransactionEntity transaction) {
         entityManager.persist(transaction);
    }

    @Override
    public List<TransactionEntity> getTransactionsByAccountId(Integer accountId) {
        TypedQuery<TransactionEntity> query = entityManager.createQuery(
                "SELECT t FROM TransactionEntity t " +
                        "LEFT JOIN FETCH t.originating acc1 " +
                        "LEFT JOIN FETCH acc1.bankEntity " +
                        "LEFT JOIN FETCH t.resulting acc2 " +
                        "LEFT JOIN FETCH acc2.bankEntity " +
                        "WHERE acc1.id = :accountId OR acc2.id = :accountId",
                TransactionEntity.class);

        query.setParameter("accountId", accountId);
        return query.getResultList();
    }

}
