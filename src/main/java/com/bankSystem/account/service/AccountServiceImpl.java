package com.bankSystem.account.service;

import com.bankSystem.account.converter.AccountConverter;
import com.bankSystem.account.dto.AccountRequest;
import com.bankSystem.account.dto.AccountResponse;
import com.bankSystem.account.dto.TransferRequest;
import com.bankSystem.account.dto.WithdrawDepositRequest;
import com.bankSystem.account.entity.AccountEntity;
import com.bankSystem.account.repository.AccountRepository;
import com.bankSystem.bank.entity.BankEntity;
import com.bankSystem.bank.service.BankService;
import com.bankSystem.exception.BadRequestException;
import com.bankSystem.exception.NotFoundException;
import com.bankSystem.transaction.dto.TransactionResponse;
import com.bankSystem.transaction.entity.TransactionEntity;
import com.bankSystem.transaction.service.TransactionService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final BankService bankService;
    private final TransactionService transactionService;

    @Override
    public void createAccount(AccountRequest request) {
        BankEntity bankEntity = bankService.findBankEntityById(request.getBankId());
        if(bankEntity == null) throw new NotFoundException("Bank not found");
        if(request.getBalance() < 0) throw new BadRequestException("Invalid balance");
        if(request.getUser_name() == null || request.getUser_name().isEmpty()) throw new BadRequestException("Invalid user name");
        AccountEntity entity = AccountConverter.toEntity(request);
        entity.setBankEntity(bankEntity);
        accountRepository.addAccount(entity);
    }

    @Override
    public void deposit(WithdrawDepositRequest request) {

        double amount = formatNumber(request.getAmount());

        AccountEntity account = accountRepository.findAccountById(request.getAccountId());
        if (account == null) throw new NotFoundException("Account not found");
        if (request.getReason() == null || request.getReason().isEmpty())
            throw new BadRequestException("Reason is empty");
        BankEntity bankEntity = account.getBankEntity();

        if (request.getAmount() < bankEntity.getTransactionFlatFeeAmount())
            throw new BadRequestException("Bank does not accept amount less than: " + bankEntity.getTransactionFlatFeeAmount() + "$");

        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setOriginating(account);
        transactionEntity.setResulting(account);
        transactionEntity.setReason(request.getReason());
        transactionEntity.setAmount(amount);
        bankEntity.setTotalTransferAmount(bankEntity.getTotalTransferAmount() + amount);

        if (bankEntity.getTransactionFlatFeeAmount() < amount * bankEntity.getTransactionPercentFeeValue()/100) {
            account.setBalance(formatNumber(account.getBalance() + amount - amount * bankEntity.getTransactionPercentFeeValue()/100));
            bankEntity.setTotalTransactionFeeAmount(formatNumber(bankEntity.getTotalTransactionFeeAmount() + (amount * bankEntity.getTransactionPercentFeeValue()/100)));
        } else {
            account.setBalance(account.getBalance() + amount - bankEntity.getTransactionFlatFeeAmount());
            bankEntity.setTotalTransactionFeeAmount(bankEntity.getTotalTransactionFeeAmount() + bankEntity.getTransactionFlatFeeAmount());
        }
        transactionService.addTransaction(transactionEntity);
        bankService.updateBank(bankEntity);
        accountRepository.updateAccount(account);

    }

    @Override
    public void withdrawal(WithdrawDepositRequest request) {
        double amount = formatNumber(request.getAmount());
        AccountEntity account = accountRepository.findAccountById(request.getAccountId());

        if (account == null) throw new NotFoundException("Account not found");
        if (request.getReason() == null || request.getReason().isEmpty())
            throw new BadRequestException("Reason is empty");

        if (request.getAmount() < 0) throw new BadRequestException("You can not withdraw negative amount");

        BankEntity bankEntity = account.getBankEntity();
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setOriginating(account);
        transactionEntity.setResulting(account);
        transactionEntity.setReason(request.getReason());
        transactionEntity.setAmount(amount);
        bankEntity.setTotalTransferAmount(bankEntity.getTotalTransferAmount() + amount);

        if (bankEntity.getTransactionFlatFeeAmount() < amount * bankEntity.getTransactionPercentFeeValue()/100) {
            if (account.getBalance() - amount - amount * bankEntity.getTransactionPercentFeeValue()/100 >= 0) {
                account.setBalance(formatNumber(account.getBalance() - amount - (amount * bankEntity.getTransactionPercentFeeValue()/100)));
                bankEntity.setTotalTransactionFeeAmount(formatNumber(bankEntity.getTotalTransactionFeeAmount() + (amount * bankEntity.getTransactionPercentFeeValue()/100)));
            } else throw new BadRequestException("Not enough money to withdraw");
        } else {
            if (account.getBalance() - amount - bankEntity.getTransactionFlatFeeAmount() >= 0) {
                account.setBalance(account.getBalance() - amount - bankEntity.getTransactionFlatFeeAmount());
                bankEntity.setTotalTransactionFeeAmount(bankEntity.getTotalTransactionFeeAmount() + bankEntity.getTransactionFlatFeeAmount());
            } else throw new BadRequestException("Not enough money to withdraw");
        }
        transactionService.addTransaction(transactionEntity);
        bankService.updateBank(bankEntity);
        accountRepository.updateAccount(account);
    }

    @Override
    public void transfer(TransferRequest transferRequest) {
        double amount = formatNumber(transferRequest.getAmount());

        AccountEntity accountFrom = accountRepository.findAccountById(transferRequest.getOriginatingAccountId());
        AccountEntity accountTo = accountRepository.findAccountById(transferRequest.getResultingAccountId());

        if (accountFrom == null) throw new NotFoundException("Your account not found");
        if (accountTo == null) throw new NotFoundException("Receiver account not found");
        if (transferRequest.getReason() == null || transferRequest.getReason().isEmpty()) throw new BadRequestException("Reason is empty");
        BankEntity bankFrom = accountFrom.getBankEntity();
        BankEntity bankTo = accountTo.getBankEntity();

        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setOriginating(accountFrom);
        transactionEntity.setResulting(accountTo);
        transactionEntity.setReason(transferRequest.getReason());
        transactionEntity.setAmount(amount);

        bankTo.setTotalTransferAmount(bankTo.getTotalTransferAmount() + amount);
        bankFrom.setTotalTransferAmount(bankFrom.getTotalTransferAmount() + amount);
        accountTo.setBalance(accountTo.getBalance() + amount);

        if (bankFrom.getTransactionFlatFeeAmount() < amount * bankFrom.getTransactionPercentFeeValue()/100) {
            if (accountFrom.getBalance() - amount - amount * bankFrom.getTransactionPercentFeeValue()/100 >= 0) {
                accountFrom.setBalance(formatNumber(accountFrom.getBalance() - amount - (amount * bankFrom.getTransactionPercentFeeValue()/100)));
                bankFrom.setTotalTransactionFeeAmount(formatNumber(bankFrom.getTotalTransactionFeeAmount() + (amount * bankFrom.getTransactionPercentFeeValue()/100)));
            } else throw new BadRequestException("Not enough money to withdraw");
        } else {
            if (accountFrom.getBalance() - amount - bankFrom.getTransactionFlatFeeAmount() >= 0) {
                accountFrom.setBalance(accountFrom.getBalance() - amount - bankFrom.getTransactionFlatFeeAmount());
                bankFrom.setTotalTransactionFeeAmount(bankFrom.getTotalTransactionFeeAmount() + bankFrom.getTransactionFlatFeeAmount());
            } else throw new BadRequestException("Not enough money to withdraw");
        }

        transactionService.addTransaction(transactionEntity);
        bankService.updateBank(bankFrom);
        bankService.updateBank(bankTo);
        accountRepository.updateAccount(accountFrom);
        accountRepository.updateAccount(accountTo);

    }

    @Override
    public AccountResponse findAccountById(Integer id) {
        return AccountConverter.toResponse(accountRepository.findAccountById(id));
    }

    @Override
    public List<AccountResponse> findAllAccounts() {
        return AccountConverter.toResponse(accountRepository.findAllAccounts());
    }

    @Override
    public List<AccountResponse> findAllAccountsByBankId(Integer bankId) {
        return AccountConverter.toResponse(accountRepository.findAllAccountsByBankId(bankId));
    }

    @Override
    public List<TransactionResponse> getTransactionsByAccountId(Integer accountId) {
        return transactionService.getTransactionByAccountId(accountId);
    }

    @Override
    public Double checkAccountBalance(Integer accountId) {
        return accountRepository.getBalance(accountId);
    }

    private Double formatNumber(Double number) {
        if (number != null) return Double.parseDouble(new DecimalFormat("#.00").format(number));
        throw new BadRequestException("amount is not valid!");
    }


}
