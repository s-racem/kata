package com.example.kata.service;

import com.example.kata.Constants;
import com.example.kata.domain.enums.TransactionType;
import com.example.kata.domain.models.Transaction;
import org.junit.Test;

import java.util.Date;

/**
 * Created by sracem on 12/08/2017.
 */
public class TransactionServiceTest {

    @Test
    public void should_add_deposit_transaction() throws Exception {
        Transaction depositTransaction = Transaction.builder()
                .amount(Constants.DEPOSIT_AMMOUNT)
                .transactionType(TransactionType.DEPOSIT)
                .date(new Date())
                .account(account)
                .build();
        accountService.addTransaction(depositTransaction);

    }
}
