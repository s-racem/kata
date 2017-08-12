package com.example.kata.exception;

/**
 * Created by sracem on 12/08/2017.
 */
public class KanaException extends RuntimeException {
    private String message;

    public KanaException(String message) {
        this.message = message;
    }
}
