package com.maximaseguranca.apibank.exception;

public class UsuarioNaoEncontradoException extends RuntimeException{
    public UsuarioNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
