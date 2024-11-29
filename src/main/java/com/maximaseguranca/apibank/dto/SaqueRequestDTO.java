package com.maximaseguranca.apibank.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;


public class SaqueRequestDTO {

    @Schema(description="Informar o número da conta", example = "000123")
    private String numeroConta;

    @Schema(description="Informar o valor para Saque", example = "300.00")
    private BigDecimal valor;

    public SaqueRequestDTO(String numeroConta, BigDecimal valor) {
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
