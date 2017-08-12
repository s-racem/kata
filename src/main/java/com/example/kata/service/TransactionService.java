package com.example.kata.service;

import com.example.kata.domain.enums.TransactionType;
import com.example.kata.domain.models.Account;
import com.example.kata.domain.models.Transaction;
import com.example.kata.exception.KanaException;
import com.example.kata.repository.AccountRepository;
import com.example.kata.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sracem on 12/08/2017.
 */
@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    AccountRepository accountRepository;
    public Transaction addTransaction(Transaction transaction) throws KanaException{
        Account account = transaction.getAccount();
        if (accountRepository.findByNumber(account.getNumber()) == null) {
            throw new KanaException("Failed transaction: account not exist");
        }
        if (TransactionType.WITHDRAWAL.equals(transaction.getTransactionType())) {
            Long balance = transactionRepository.calculateAccountBalance(transaction.getAccount());
            if (balance < transaction.getAmount()) {
                throw new KanaException("Failed transaction: Insufficient balance");
            }
            transaction.setAmount(transaction.getAmount()* -1);
        }
        return transactionRepository.save(transaction);
    }
}
