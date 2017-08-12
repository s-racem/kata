package com.example.kata.repository;

import com.example.kata.domain.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by sracem on 12/08/2017.
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByNumber(String number);
}