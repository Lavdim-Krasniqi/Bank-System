package com.bankSystem.bank.repository;

import com.bankSystem.bank.entity.BankEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@Repository
@AllArgsConstructor
public class BankRepositoryImpl implements BankRepository{

    private EntityManager entityManager;


    @Override
    public void addBank(BankEntity entity) {
        entityManager.persist(entity);
    }

    @Override
    public BankEntity updateBank(BankEntity entity) {
        return entityManager.merge(entity);
    }

    @Override
    public BankEntity findById(Integer id) {
        return entityManager.find(BankEntity.class, id);
    }

    @Override
    public List<BankEntity> findAll() {
        return entityManager.createQuery("select b from BankEntity b", BankEntity.class).getResultList();
    }

    @Override
    public Double getTotalTransferAmount(Integer bankId) {
        TypedQuery<Double> query = entityManager.createQuery("select b.totalTransferAmount from BankEntity b where b.id =:bankId", Double.class);
        query.setParameter("bankId", bankId);
        return query.getSingleResult();
    }

    @Override
    public Double getTotalTransactionFeeAmount(Integer bankId) {
        TypedQuery<Double> query = entityManager.createQuery("select b.totalTransactionFeeAmount from BankEntity b where b.id =:bankId", Double.class);
        query.setParameter("bankId", bankId);
        return query.getSingleResult();
    }
}
