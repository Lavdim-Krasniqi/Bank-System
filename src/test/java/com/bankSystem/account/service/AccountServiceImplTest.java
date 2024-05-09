package com.bankSystem.account.service;


import com.bankSystem.account.dto.AccountRequest;
import com.bankSystem.account.dto.WithdrawDepositRequest;
import com.bankSystem.account.entity.AccountEntity;
import com.bankSystem.account.repository.AccountRepository;
import com.bankSystem.bank.entity.BankEntity;
import com.bankSystem.bank.service.BankService;
import com.bankSystem.exception.BadRequestException;
import com.bankSystem.exception.NotFoundException;
import com.bankSystem.transaction.entity.TransactionEntity;
import com.bankSystem.transaction.service.TransactionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private BankService bankService;

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private AccountServiceImpl accountService;

    private AccountRequest accountRequest;
    private AccountEntity account;
    private BankEntity bank;
    private WithdrawDepositRequest withdrawDepositRequest;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        bank = new BankEntity();
        bank.setId(1);
        bank.setTransactionFlatFeeAmount(10.0);
        bank.setTransactionPercentFeeValue(5);
        bank.setTotalTransactionFeeAmount(0.0);
        bank.setTotalTransferAmount(0.0);

        account = new AccountEntity();
        account.setId(1);
        account.setBankEntity(bank);
        account.setUser_name("John Doe");
        account.setBalance(100.0);

        accountRequest = new AccountRequest();
        accountRequest.setBankId(1);
        accountRequest.setUser_name("John Doe");
        accountRequest.setBalance(50.0);

        withdrawDepositRequest = new WithdrawDepositRequest();
        withdrawDepositRequest.setAccountId(1);
        withdrawDepositRequest.setAmount(50.0);
        withdrawDepositRequest.setReason("Gift");
    }

    // Test methods will be defined here

    @Test
    public void testCreateAccount_Success() {
        when(bankService.findBankEntityById(1)).thenReturn(bank);
        doNothing().when(accountRepository).addAccount(any(AccountEntity.class));
        accountService.createAccount(accountRequest);
        verify(accountRepository, times(1)).addAccount(any(AccountEntity.class));
    }


    @Test
    public void testCreateAccount_BankNotFoundFail() {
        when(bankService.findBankEntityById(1)).thenReturn(null);
        assertThrows(NotFoundException.class, () -> accountService.createAccount(accountRequest));
        verify(bankService, times(1)).findBankEntityById(any(Integer.class));

    }


    @Test
    public void testCreateAccount_BalanceFail() {
        accountRequest.setBalance(-1.0);
        when(bankService.findBankEntityById(1)).thenReturn(bank);
        assertThrows(BadRequestException.class, () -> accountService.createAccount(accountRequest));
        verify(bankService, times(1)).findBankEntityById(any(Integer.class));
    }

    @Test
    public void testCreateAccount_EmptyNameFail() {
        accountRequest.setUser_name("");
        when(bankService.findBankEntityById(1)).thenReturn(bank);
        assertThrows(BadRequestException.class, () -> accountService.createAccount(accountRequest));
        verify(bankService, times(1)).findBankEntityById(any(Integer.class));
    }

    @Test
    public void testWithdrawal_Success(){
        when(accountRepository.findAccountById(1)).thenReturn(account);
        doNothing().when(transactionService).addTransaction(any(TransactionEntity.class));
        accountService.withdrawal(withdrawDepositRequest);
        verify(accountRepository, times(1)).updateAccount(any(AccountEntity.class));
    }

    @Test
    public void testWithdrawal_NotEnoughMoneyFail(){
        withdrawDepositRequest.setAmount(100.0);
        when(accountRepository.findAccountById(1)).thenReturn(account);
        doNothing().when(transactionService).addTransaction(any(TransactionEntity.class));
        assertThrows(BadRequestException.class, () -> accountService.withdrawal(withdrawDepositRequest));
    }





}
