package com.example.kata.service;

import com.example.kata.Constants;
import com.example.kata.domain.enums.TransactionType;
import com.example.kata.domain.models.Account;
import com.example.kata.domain.models.Transaction;
import com.example.kata.exception.KanaException;
import com.example.kata.repository.AccountRepository;
import com.example.kata.repository.TransactionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @Mock
    private AccountRepository accountRepository;

    private Account account;
    private Transaction depositTransaction;
    private List<Transaction> transactions;

    @Before
    public void init() {
        account = Account.builder()
                .number(Constants.ACCOUNT_NUMBER)
                .build();
        depositTransaction = Transaction.builder()
                .amount(Constants.DEPOSIT_AMMOUNT)
                .transactionType(TransactionType.DEPOSIT)
                .date(new Date())
                .account(account)
                .build();
        transactions = new ArrayList<>();
        transactions.add(depositTransaction);
    }

    @Test
    public void should_add_deposit_transaction() throws Exception {
        doReturn(depositTransaction).when(transactionRepository).save(depositTransaction);
        doReturn(account).when(accountRepository).findByNumber(Constants.ACCOUNT_NUMBER);
        Transaction result = transactionService.addTransaction(depositTransaction);
        assertThat(result).isEqualToComparingFieldByField(depositTransaction);
    }

    @Test
    public void should_not_add_transaction_when_not_found_account() {
        Account newAccount = Account.builder()
                .number(Constants.NEW_ACCOUNT_NUMBER)
                .build();
        Transaction newDepositTransaction = Transaction.builder()
                .amount(Constants.DEPOSIT_AMMOUNT)
                .transactionType(TransactionType.DEPOSIT)
                .date(new Date())
                .account(newAccount)
                .build();
        doReturn(newDepositTransaction).when(transactionRepository).save(newDepositTransaction);
        doReturn(null).when(accountRepository).findByNumber(Constants.NEW_ACCOUNT_NUMBER);
        assertThatThrownBy(() -> transactionService.addTransaction(newDepositTransaction))
                .isInstanceOf(KanaException.class);
    }

    @Test
    public void should_add_withdrawal_transaction() throws Exception {
        Transaction withdrawalTransaction = Transaction.builder()
                .amount(Constants.WITHDRAWAL_AMMOUNT)
                .transactionType(TransactionType.WITHDRAWAL)
                .date(new Date())
                .account(account)
                .build();
        doReturn(withdrawalTransaction).when(transactionRepository).save(withdrawalTransaction);
        doReturn(account).when(accountRepository).findByNumber(Constants.ACCOUNT_NUMBER);
        doReturn(Constants.DEPOSIT_AMMOUNT).when(transactionRepository).calculateAccountBalance(account);
        Transaction result = transactionService.addTransaction(withdrawalTransaction);
        assertThat(result).isEqualToComparingFieldByField(withdrawalTransaction);
    }

    @Test
    public void should_not_autorize_withdrawal_transaction() throws Exception {
        Transaction withdrawalTransaction = Transaction.builder()
                .amount(Constants.WITHDRAWAL_UNAUTORIZED_AMMOUNT)
                .transactionType(TransactionType.WITHDRAWAL)
                .date(new Date())
                .account(account)
                .build();
        doReturn(withdrawalTransaction).when(transactionRepository).save(withdrawalTransaction);
        doReturn(account).when(accountRepository).findByNumber(Constants.ACCOUNT_NUMBER);
        doReturn(Constants.DEPOSIT_AMMOUNT).when(transactionRepository).calculateAccountBalance(account);
        assertThatThrownBy(() -> transactionService.addTransaction(withdrawalTransaction))
                .isInstanceOf(KanaException.class);
    }

    @Test
    public void should_fetch_all_transactions_by_account() throws Exception {
        doReturn(transactions).when(transactionRepository).findByAccount(account);
        doReturn(account).when(accountRepository).findByNumber(Constants.ACCOUNT_NUMBER);
        List<Transaction> result = transactionService.findByAccount(account);
        assertThat(result).isNotNull().isNotEmpty().hasSize(1).containsExactly(depositTransaction);
    }
}
