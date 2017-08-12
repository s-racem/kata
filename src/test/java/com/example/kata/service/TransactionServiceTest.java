package com.example.kata.service;

import com.example.kata.Constants;
import com.example.kata.domain.enums.TransactionType;
import com.example.kata.domain.models.Account;
import com.example.kata.domain.models.Transaction;
import com.example.kata.exception.KanaException;
import com.example.kata.repository.TransactionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doReturn;

/**
 * Created by sracem on 12/08/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {
    @Spy
    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    private Account account;

    @Before
    public void init() {
        account = Account.builder()
                .number(Constants.ACCOUNT_NUMBER)
                .build();
    }

    @Test
    public void should_add_deposit_transaction() throws Exception {
        Transaction depositTransaction = Transaction.builder()
                .amount(Constants.DEPOSIT_AMMOUNT)
                .transactionType(TransactionType.DEPOSIT)
                .date(new Date())
                .account(account)
                .build();
        doReturn(depositTransaction).when(transactionRepository).save(depositTransaction);
        Transaction result = transactionService.addTransaction(depositTransaction);
        assertThat(result).isEqualToComparingFieldByField(depositTransaction);
    }

    @Test
    public void should_not_add_transaction_when_not_found_account() {
        Account newAccount = Account.builder()
                .number(Constants.NEW_ACCOUNT_NUMBER)
                .build();
        Transaction depositTransaction = Transaction.builder()
                .amount(Constants.DEPOSIT_AMMOUNT)
                .transactionType(TransactionType.DEPOSIT)
                .date(new Date())
                .account(newAccount)
                .build();
        doReturn(depositTransaction).when(transactionRepository).save(depositTransaction);
        Transaction result = transactionService.addTransaction(depositTransaction);
        assertThat(result).isEqualToComparingFieldByField(depositTransaction);

        assertThatThrownBy(() -> transactionService.addTransaction(depositTransaction))
                .isInstanceOf(KanaException.class);
    }
}
