package com.example.kata.service;

import com.example.kata.domain.models.Transaction;
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
    
    public Transaction addTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
}
