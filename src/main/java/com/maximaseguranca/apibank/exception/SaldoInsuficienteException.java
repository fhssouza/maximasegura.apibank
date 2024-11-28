package com.maximaseguranca.apibank.exception;

public class SaldoInsuficienteException extends RuntimeException{
    public SaldoInsuficienteException(String message) {
        super(message);
    }
}
