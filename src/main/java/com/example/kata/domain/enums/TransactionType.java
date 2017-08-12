package com.example.kata.domain.enums;

/**
 * Created by sracem on 12/08/2017.
 */
public enum TransactionType {
    DEPOSIT("Deposit");
    private String value;

    TransactionType(String value) {
        this.value = value;
    }
}
