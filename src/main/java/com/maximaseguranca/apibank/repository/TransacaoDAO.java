package com.maximaseguranca.apibank.repository;

import com.maximaseguranca.apibank.model.Usuario;

import java.math.BigDecimal;
import java.util.Optional;

public interface TransacaoDAO {
    void realizarDeposito(String numeroConta, BigDecimal valor);
    void realizarSaque(String numeroConta, BigDecimal valor);
    Optional<Usuario> buscarPorNumeroConta(String numeroConta);
    void debitarSaldo(String numeroContaOrigem, BigDecimal valor);
    void creditarSaldo(String numeroContaDestino, BigDecimal valor);
    void transferir(String numeroContaOrigem, String numeroContaDestino, BigDecimal valor);
}
