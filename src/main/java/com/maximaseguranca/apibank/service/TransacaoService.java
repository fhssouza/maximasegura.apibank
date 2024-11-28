package com.maximaseguranca.apibank.service;

import com.maximaseguranca.apibank.dto.TransferenciaRequestDTO;
import com.maximaseguranca.apibank.exception.ContaNaoEncontradaException;
import com.maximaseguranca.apibank.exception.SaldoInsuficienteException;
import com.maximaseguranca.apibank.model.Usuario;
import com.maximaseguranca.apibank.repository.TransacaoDAO;
import com.maximaseguranca.apibank.repository.UsuarioDAO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransacaoService {

    @Autowired
    TransacaoDAO transacaoDAO;

    @Autowired
    UsuarioDAO usuarioDAO;

    @Transactional
    public void realizarTransferencia(TransferenciaRequestDTO transferenciaRequestDTO) {

        Optional<Usuario> contaOrigemOpt = usuarioDAO.buscarPorNumeroConta(transferenciaRequestDTO.getNumeroContaOrigem());
        if (contaOrigemOpt.isEmpty()) {
            throw new ContaNaoEncontradaException("Conta de origem " + transferenciaRequestDTO.getNumeroContaOrigem() + " não encontrada.");
        }

        Optional<Usuario> contaDestinoOpt = usuarioDAO.buscarPorNumeroConta(transferenciaRequestDTO.getNumeroContaDestino());
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
