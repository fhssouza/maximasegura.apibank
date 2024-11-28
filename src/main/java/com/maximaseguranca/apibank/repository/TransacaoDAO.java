package com.maximaseguranca.apibank.repository;

import java.math.BigDecimal;

public interface TransacaoDAO {
    void debitarSaldo(String numeroContaOrigem, BigDecimal valor);
    void creditarSaldo(String numeroContaDestino, BigDecimal valor);
    void transferir(String numeroContaOrigem, String numeroContaDestino, BigDecimal valor);
}
