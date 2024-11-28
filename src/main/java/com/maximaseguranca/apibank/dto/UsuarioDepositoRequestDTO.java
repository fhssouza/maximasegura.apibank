package com.maximaseguranca.apibank.dto;

import java.math.BigDecimal;


public class UsuarioDepositoRequestDTO {

    private String numeroConta;
    private BigDecimal valor;

    public UsuarioDepositoRequestDTO(String numeroConta, BigDecimal valor) {
        this.numeroConta = numeroConta;
        this.valor = valor;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
