package com.maximaseguranca.apibank.dto;

import java.math.BigDecimal;

public class TransferenciaRequestDTO {

    private String numeroContaOrigem;
    private String numeroContaDestino;
    private BigDecimal valor;

    public TransferenciaRequestDTO(String numeroContaOrigem, String numeroContaDestino, BigDecimal valor) {
        this.numeroContaOrigem = numeroContaOrigem;
        this.numeroContaDestino = numeroContaDestino;
        this.valor = valor;
    }

    public String getNumeroContaOrigem() {
        return numeroContaOrigem;
    }

    public void setNumeroContaOrigem(String numeroContaOrigem) {
        this.numeroContaOrigem = numeroContaOrigem;
    }

    public String getNumeroContaDestino() {
        return numeroContaDestino;
    }

    public void setNumeroContaDestino(String numeroContaDestino) {
        this.numeroContaDestino = numeroContaDestino;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
