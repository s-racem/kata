package com.example.kata.repository;

import com.example.kata.Constants;
import com.example.kata.domain.models.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * Created by sracem on 12/08/2017.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class TransactionRepositoryTest {

    @Test
    public void should_add_deposit_transaction() throws Exception {
        Transaction depositTransaction = Transaction.builder()
                .amount(Constants.DEPOSIT_AMMOUNT)
                .transactionType(TransactionType.DEPOSIT)
                .date(new Date())
                .account(account)
                .build();
    }
}
