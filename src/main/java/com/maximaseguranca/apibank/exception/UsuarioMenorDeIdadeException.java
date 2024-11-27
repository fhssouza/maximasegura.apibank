package com.maximaseguranca.apibank.exception;

public class UsuarioMenorDeIdadeException extends RuntimeException {

    public UsuarioMenorDeIdadeException(String mensagem) {
        super(mensagem);
    }
}
