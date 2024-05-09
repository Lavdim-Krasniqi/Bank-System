package com.bankSystem.account.repository;

import com.bankSystem.account.entity.AccountEntity;
import com.bankSystem.exception.NotFoundException;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class AccountRepositoryImpl implements AccountRepository {
    private final EntityManager entityManager;
    @Override
    public void addAccount(AccountEntity entity) {
        entityManager.persist(entity);
    }

    @Override
    public void updateAccount(AccountEntity entity) {
        entityManager.merge(entity);
    }

    @Override
    public AccountEntity findAccountById(Integer id) {
        return entityManager.createQuery("select acc from AccountEntity acc left join fetch acc.bankEntity where acc.id =:id", AccountEntity.class)
                .setParameter("id", id).getSingleResult();
    }

    @Override
    public List<AccountEntity> findAllAccounts() {
        return entityManager.createQuery("select a from AccountEntity a left join fetch a.bankEntity", AccountEntity.class).getResultList();
    }

    @Override
    public List<AccountEntity> findAllAccountsByBankId(Integer bankId) {
         return entityManager.createQuery("select a from AccountEntity a" +
                                      " left join fetch a.bankEntity b where b.id = :bankId", AccountEntity.class)
                                       .setParameter("bankId", bankId).getResultList();
    }

    @Override
    public Double getBalance(Integer accountId) {
        return entityManager.createQuery("select a.balance from AccountEntity a", Double.class).getSingleResult();
    }

}
