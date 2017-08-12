package com.example.kata.service;

import com.example.kata.Constants;
import com.example.kata.domain.enums.TransactionType;
import com.example.kata.domain.models.Account;
import com.example.kata.domain.models.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by sracem on 12/08/2017.
 */
public class TransactionServiceTest {
    @Spy
    @InjectMocks
    private TransactionService transactionService;

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
        Transaction result = transactionService.addTransaction(depositTransaction);
        assertThat(result).isEqualToComparingFieldByField(depositTransaction);

    }
}
