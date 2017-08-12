package com.example.kata.repository;

import com.example.kata.Constants;
import com.example.kata.domain.enums.TransactionType;
import com.example.kata.domain.models.Account;
import com.example.kata.domain.models.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by sracem on 12/08/2017.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class TransactionRepositoryTest {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    private TestEntityManager testEntityManager;

    private Account account;

    @Before
    public void init() {
        account = Account.builder()
                .number(Constants.ACCOUNT_NUMBER)
                .build();
        testEntityManager.persist(account);
    }

    @Test
    public void should_add_deposit_transaction() throws Exception {

        Transaction depositTransaction = Transaction.builder()
                .amount(Constants.DEPOSIT_AMMOUNT)
                .transactionType(TransactionType.DEPOSIT)
                .date(new Date())
                .account(account)
                .build();
        Transaction result = transactionRepository.saveAndFlush(depositTransaction);
        assertThat(result)
                .isNotNull()
                .isEqualToComparingFieldByField(depositTransaction);
    }

    @Test
    public void should_make_withdrawal_transaction() throws Exception {

        Transaction depositTransaction = Transaction.builder()
                .amount(Constants.DEPOSIT_AMMOUNT)
                .transactionType(TransactionType.WITHDRAWAL)
                .date(new Date())
                .account(account)
                .build();
        Transaction result = transactionRepository.saveAndFlush(depositTransaction);
        assertThat(result)
                .isNotNull()
                .isEqualToComparingFieldByField(depositTransaction);
    }
}
