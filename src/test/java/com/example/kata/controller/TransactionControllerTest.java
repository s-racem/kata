package com.example.kata.controller;

import com.example.kata.Constants;
import com.example.kata.domain.enums.TransactionType;
import com.example.kata.domain.models.Account;
import com.example.kata.domain.models.Transaction;
import com.example.kata.service.TransactionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by sracem on 13/08/2017.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(TrasactionController.class)
public class TransactionControllerTest {

    @MockBean
    TransactionService transactionService;

    private MockMvc mockMvc;

    private Account account;
    private Transaction depositTransaction;

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
    }

    @Test
    public void should_add_deposit_transaction() throws Exception {
        doReturn(depositTransaction).when(transactionService).addTransaction(depositTransaction);
        mockMvc.perform(post("/api/addTransaction")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .sessionAttr("transactopn", depositTransaction))
                .andExpect(status().isOk());
    }
}
