package com.maximaseguranca.apibank.service;

import com.maximaseguranca.apibank.dto.DepositoRequestDTO;
import com.maximaseguranca.apibank.dto.SaqueRequestDTO;
import com.maximaseguranca.apibank.dto.TransferenciaRequestDTO;
import com.maximaseguranca.apibank.exception.ContaNaoEncontradaException;
import com.maximaseguranca.apibank.exception.SaldoInsuficienteException;
import com.maximaseguranca.apibank.exception.ValorInvalidoException;
import com.maximaseguranca.apibank.model.Usuario;
import com.maximaseguranca.apibank.repository.TransacaoDAO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class TransacaoService {

    @Autowired
    TransacaoDAO transacaoDAO;

    @Transactional
    public void realizarDeposito(DepositoRequestDTO depositoRequestDTO) {

        if (depositoRequestDTO.getValor().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValorInvalidoException("O valor do depósito deve ser maior que 0.00.");
        }

        Optional<Usuario> usuarioOptional = transacaoDAO.buscarPorNumeroConta(depositoRequestDTO.getNumeroConta());

        if (usuarioOptional.isEmpty()) {
            throw new ContaNaoEncontradaException("Conta com número " + depositoRequestDTO.getNumeroConta() + " não encontrada.");
        }

        transacaoDAO.realizarDeposito(depositoRequestDTO.getNumeroConta(), depositoRequestDTO.getValor());
    }

    @Transactional
    public void realizarSaque(SaqueRequestDTO saqueRequestDTO) {

        if (saqueRequestDTO.getValor().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValorInvalidoException("O valor do saque deve ser maior que 0.00.");
        }

        if (saqueRequestDTO.getValor().compareTo(BigDecimal.ZERO) < 0) {
            throw new SaldoInsuficienteException("Saldo insuficiente para a transferência.");
        }

        Optional<Usuario> usuarioOptional = transacaoDAO.buscarPorNumeroConta(saqueRequestDTO.getNumeroConta());

        if (usuarioOptional.isEmpty()) {
            throw new ContaNaoEncontradaException("Conta com número " + saqueRequestDTO.getNumeroConta() + " não encontrada.");
        }

        transacaoDAO.realizarSaque(saqueRequestDTO.getNumeroConta(), saqueRequestDTO.getValor());
    }

    @Transactional
    public void realizarTransferencia(TransferenciaRequestDTO transferenciaRequestDTO) {

        Optional<Usuario> contaOrigemOpt = transacaoDAO.buscarPorNumeroConta(transferenciaRequestDTO.getNumeroContaOrigem());
        if (contaOrigemOpt.isEmpty()) {
            throw new ContaNaoEncontradaException("Conta de origem " + transferenciaRequestDTO.getNumeroContaOrigem() + " não encontrada.");
        }

        Optional<Usuario> contaDestinoOpt = transacaoDAO.buscarPorNumeroConta(transferenciaRequestDTO.getNumeroContaDestino());
        if (contaDestinoOpt.isEmpty()) {
            throw new ContaNaoEncontradaException("Conta de destino " + transferenciaRequestDTO.getNumeroContaDestino() + " não encontrada.");
        }

        Usuario contaOrigem = contaOrigemOpt.get();
        if (contaOrigem.getSaldo().compareTo(transferenciaRequestDTO.getValor()) < 0) {
            throw new SaldoInsuficienteException("Saldo insuficiente para a transferência.");
        }

        transacaoDAO.transferir(transferenciaRequestDTO.getNumeroContaOrigem(), transferenciaRequestDTO.getNumeroContaDestino(), transferenciaRequestDTO.getValor());
    }
}
