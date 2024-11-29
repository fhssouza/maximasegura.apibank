package com.maximaseguranca.apibank.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public class TransferenciaRequestDTO {

    @Schema(description="Informar o numero da conta de Origem", example = "000123")
    private String numeroContaOrigem;

    @Schema(description="Informar o numero da conta de Destino", example = "000456")
    private String numeroContaDestino;

    @Schema(description="Informar o valor para transferência", example = "100.00")
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
