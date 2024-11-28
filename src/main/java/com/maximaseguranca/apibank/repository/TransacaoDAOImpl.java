package com.maximaseguranca.apibank.repository;

import com.maximaseguranca.apibank.model.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class TransacaoDAOImpl implements TransacaoDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void realizarDeposito(String numeroConta, BigDecimal valor) {
        String sql = "UPDATE usuarios SET saldo = saldo + :valor WHERE numero_conta = :numeroConta";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("numeroConta", numeroConta);
        query.setParameter("valor", valor);

        query.executeUpdate();
    }

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

    @Override
    public Optional<Usuario> buscarPorNumeroConta(String numeroConta) {
        String sql = "SELECT * FROM usuarios WHERE numero_conta = :numeroConta";
        try {
            List<Usuario> usuarios = entityManager.createNativeQuery(sql, Usuario.class)
                    .setParameter("numeroConta", numeroConta)
                    .getResultList();
            if (usuarios.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(usuarios.get(0));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
