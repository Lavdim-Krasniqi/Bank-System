package com.bankSystem.bank.service;

import com.bankSystem.bank.converter.BankConverter;
import com.bankSystem.bank.dto.BankRequest;
import com.bankSystem.bank.dto.BankResponse;
import com.bankSystem.bank.entity.BankEntity;
import com.bankSystem.bank.repository.BankRepository;
import com.bankSystem.exception.BadRequestException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class BankServiceImpl implements BankService {

    private BankRepository bankRepository;


    @Override
    public void addBank(BankRequest request) {
        if (request.getTotalTransactionFeeAmount() == null && request.getTransactionPercentFeeValue() == null) {
            throw new BadRequestException("You must specify transaction fee");
        }
        if(request.getName() == null || request.getName().isEmpty()) throw new BadRequestException("You must specify name");
        if (request.getTotalTransactionFeeAmount() == null) request.setTotalTransactionFeeAmount(0.0);
        if (request.getTotalTransferAmount() == null) request.setTotalTransferAmount(0.0);
        bankRepository.addBank(BankConverter.toEntity(request));
    }

    @Override
    public BankResponse findById(Integer id) {
        return BankConverter.toResponse(bankRepository.findById(id));
    }

    @Override
    public List<BankResponse> findAll() {
        return BankConverter.toResponse(bankRepository.findAll());
    }

    @Override
    public Double getTotalTransferAmount(Integer bankId) {
        return bankRepository.getTotalTransferAmount(bankId);
    }

    @Override
    public Double getTotalTransactionFeeAmount(Integer bankId) {
        return bankRepository.getTotalTransactionFeeAmount(bankId);
    }

    @Override
    public BankEntity updateBank(BankEntity entity) {
        return bankRepository.updateBank(entity);
    }

    @Override
    public BankEntity findBankEntityById(Integer id) {
        return bankRepository.findById(id);
    }
}
