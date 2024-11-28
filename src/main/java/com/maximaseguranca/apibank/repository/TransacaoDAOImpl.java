package com.maximaseguranca.apibank.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public class TransacaoDAOImpl implements TransacaoDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void debitarSaldo(String numeroContaOrigem, BigDecimal valor) {
        String sql = "UPDATE usuarios SET saldo = saldo - :valor WHERE numero_conta = :numeroContaOrigem";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("valor", valor);
        query.setParameter("numeroContaOrigem", numeroContaOrigem);

        query.executeUpdate();

    }

    @Override
    public void creditarSaldo(String numeroContaDestino, BigDecimal valor) {
        String sql = "UPDATE usuarios SET saldo = saldo + :valor WHERE numero_conta = :numeroContaDestino";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("valor", valor);
        query.setParameter("numeroContaDestino", numeroContaDestino);

        query.executeUpdate();

    }

    @Override
    public void transferir(String numeroContaOrigem, String numeroContaDestino, BigDecimal valor) {
        debitarSaldo(numeroContaOrigem, valor);

        creditarSaldo(numeroContaDestino, valor);
    }
}
