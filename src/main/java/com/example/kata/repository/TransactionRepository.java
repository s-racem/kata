package com.example.kata.repository;

import com.example.kata.domain.models.Account;
import com.example.kata.domain.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sracem on 12/08/2017.
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccount(Account account);

    @Query("select sum(t.amount) from Transaction t where t.account = ?1")
    Long calculateAccountBalance(Account account);
}
