package com.example.kata.controller;

import com.example.kata.domain.models.Account;
import com.example.kata.domain.models.Transaction;
import com.example.kata.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by sracem on 13/08/2017.
 */
@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @RequestMapping(method = RequestMethod.POST, path = "/api/addTransaction")
    public Transaction addTransaction(Transaction transaction) {
        return transactionService.addTransaction(transaction);
    }
    @RequestMapping(method = RequestMethod.GET, path = "/api/findByAccount")
    public List<Transaction> findByAccount(Account account) {
        return transactionService.findByAccount(account);
    }

}
