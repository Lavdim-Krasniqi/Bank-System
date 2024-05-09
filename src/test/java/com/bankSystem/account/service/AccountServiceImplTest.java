package com.bankSystem.account.service;


import com.bankSystem.account.dto.AccountRequest;
import com.bankSystem.account.entity.AccountEntity;
import com.bankSystem.account.repository.AccountRepository;
import com.bankSystem.bank.entity.BankEntity;
import com.bankSystem.bank.service.BankService;
import com.bankSystem.transaction.service.TransactionService;
import org.junit.Before;
//import org.junit.Test;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
//@SpringBootTest
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

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        bank = new BankEntity();
        bank.setId(1);
        bank.setTransactionFlatFeeAmount(5.0);
        bank.setTransactionPercentFeeValue(1);

        account = new AccountEntity();
        account.setId(1);
        account.setBankEntity(bank);
        account.setUser_name("John Doe");
        account.setBalance(100.0);

        accountRequest = new AccountRequest();
        accountRequest.setBankId(1);
        accountRequest.setUser_name("John Doe");
        accountRequest.setBalance(50.0);
    }

    // Test methods will be defined here

    @Test
    public void testCreateAccount_Success() {
        when(bankService.findBankEntityById(1)).thenReturn(bank);

        doNothing().when(accountRepository).addAccount(any(AccountEntity.class));

        accountService.createAccount(accountRequest);
//
        verify(accountRepository, times(1)).addAccount(any(AccountEntity.class));
        assertThat(accountService).isNotNull();
    }


}
