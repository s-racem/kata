package com.example.kata.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Created by sracem on 12/08/2017.
 */
@Builder
@Entity
@Table(name = "ACCOUNT")
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Account {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "NUMBER", nullable = false)
    private String number;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Transaction> transactions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
