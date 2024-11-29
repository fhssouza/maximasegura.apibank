package com.maximaseguranca.apibank;

import com.maximaseguranca.apibank.dto.DepositoRequestDTO;
import com.maximaseguranca.apibank.dto.SaqueRequestDTO;
import com.maximaseguranca.apibank.dto.TransferenciaRequestDTO;
import com.maximaseguranca.apibank.exception.ContaNaoEncontradaException;
import com.maximaseguranca.apibank.exception.SaldoInsuficienteException;
import com.maximaseguranca.apibank.exception.ValorInvalidoException;
import com.maximaseguranca.apibank.model.Usuario;
import com.maximaseguranca.apibank.repository.TransacaoDAO;
import com.maximaseguranca.apibank.service.TransacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TransacaoServiceTest {

    @InjectMocks
    private TransacaoService transacaoService;

    @Mock
    private TransacaoDAO transacaoDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void realizarDeposito_comSucesso() {

        DepositoRequestDTO depositoRequestDTO = new DepositoRequestDTO("123456", BigDecimal.valueOf(100.00));
        Usuario usuario = new Usuario();
        usuario.setNumeroConta("123456");

        when(transacaoDAO.buscarPorNumeroConta(depositoRequestDTO.getNumeroConta())).thenReturn(Optional.of(usuario));

        transacaoService.realizarDeposito(depositoRequestDTO);

        verify(transacaoDAO, times(1)).realizarDeposito(depositoRequestDTO.getNumeroConta(), depositoRequestDTO.getValor());
    }

    @Test
    void realizarDeposito_valorInvalido() {

        DepositoRequestDTO depositoRequestDTO = new DepositoRequestDTO("123456", BigDecimal.ZERO);

        assertThrows(ValorInvalidoException.class, () -> transacaoService.realizarDeposito(depositoRequestDTO));
        verify(transacaoDAO, never()).realizarDeposito(anyString(), any(BigDecimal.class));
    }

    @Test
    void realizarDeposito_contaNaoEncontrada() {

        DepositoRequestDTO depositoRequestDTO = new DepositoRequestDTO("123456", BigDecimal.valueOf(100.00));
        when(transacaoDAO.buscarPorNumeroConta(depositoRequestDTO.getNumeroConta())).thenReturn(Optional.empty());

        assertThrows(ContaNaoEncontradaException.class, () -> transacaoService.realizarDeposito(depositoRequestDTO));
        verify(transacaoDAO, never()).realizarDeposito(anyString(), any(BigDecimal.class));
    }


    @Test
    void realizarSaque_comSucesso() {

        SaqueRequestDTO saqueRequestDTO = new SaqueRequestDTO("123456", BigDecimal.valueOf(50.00));
        Usuario usuario = new Usuario();
        usuario.setNumeroConta("123456");
        usuario.setSaldo(BigDecimal.valueOf(100.00));

        when(transacaoDAO.buscarPorNumeroConta(saqueRequestDTO.getNumeroConta())).thenReturn(Optional.of(usuario));

        transacaoService.realizarSaque(saqueRequestDTO);

        verify(transacaoDAO, times(1)).realizarSaque(saqueRequestDTO.getNumeroConta(), saqueRequestDTO.getValor());
    }

    @Test
    void realizarSaque_valorInvalido() {

        SaqueRequestDTO saqueRequestDTO = new SaqueRequestDTO("123456", BigDecimal.ZERO);

        assertThrows(ValorInvalidoException.class, () -> transacaoService.realizarSaque(saqueRequestDTO));
        verify(transacaoDAO, never()).realizarSaque(anyString(), any(BigDecimal.class));
    }

    @Test
    void realizarSaque_contaNaoEncontrada() {

        SaqueRequestDTO saqueRequestDTO = new SaqueRequestDTO("123456", BigDecimal.valueOf(50.00));
        when(transacaoDAO.buscarPorNumeroConta(saqueRequestDTO.getNumeroConta())).thenReturn(Optional.empty());

        assertThrows(ContaNaoEncontradaException.class, () -> transacaoService.realizarSaque(saqueRequestDTO));
        verify(transacaoDAO, never()).realizarSaque(anyString(), any(BigDecimal.class));
    }


    @Test
    void realizarTransferencia_comSucesso() {

        TransferenciaRequestDTO transferenciaRequestDTO = new TransferenciaRequestDTO("123456", "654321", BigDecimal.valueOf(50.00));
        Usuario contaOrigem = new Usuario();
        contaOrigem.setNumeroConta("123456");
        contaOrigem.setSaldo(BigDecimal.valueOf(100.00));

        Usuario contaDestino = new Usuario();
        contaDestino.setNumeroConta("654321");

        when(transacaoDAO.buscarPorNumeroConta(transferenciaRequestDTO.getNumeroContaOrigem())).thenReturn(Optional.of(contaOrigem));
        when(transacaoDAO.buscarPorNumeroConta(transferenciaRequestDTO.getNumeroContaDestino())).thenReturn(Optional.of(contaDestino));

        transacaoService.realizarTransferencia(transferenciaRequestDTO);

        verify(transacaoDAO, times(1)).transferir(
                transferenciaRequestDTO.getNumeroContaOrigem(),
                transferenciaRequestDTO.getNumeroContaDestino(),
                transferenciaRequestDTO.getValor()
        );
    }

    @Test
    void realizarTransferencia_contaOrigemNaoEncontrada() {

        TransferenciaRequestDTO transferenciaRequestDTO = new TransferenciaRequestDTO("123456", "654321", BigDecimal.valueOf(50.00));
        when(transacaoDAO.buscarPorNumeroConta(transferenciaRequestDTO.getNumeroContaOrigem())).thenReturn(Optional.empty());

        assertThrows(ContaNaoEncontradaException.class, () -> transacaoService.realizarTransferencia(transferenciaRequestDTO));
        verify(transacaoDAO, never()).transferir(anyString(), anyString(), any(BigDecimal.class));
    }

    @Test
    void realizarTransferencia_contaDestinoNaoEncontrada() {

        TransferenciaRequestDTO transferenciaRequestDTO = new TransferenciaRequestDTO("123456", "654321", BigDecimal.valueOf(50.00));
        Usuario contaOrigem = new Usuario();
        contaOrigem.setNumeroConta("123456");
        contaOrigem.setSaldo(BigDecimal.valueOf(100.00));

        when(transacaoDAO.buscarPorNumeroConta(transferenciaRequestDTO.getNumeroContaOrigem())).thenReturn(Optional.of(contaOrigem));
        when(transacaoDAO.buscarPorNumeroConta(transferenciaRequestDTO.getNumeroContaDestino())).thenReturn(Optional.empty());

        assertThrows(ContaNaoEncontradaException.class, () -> transacaoService.realizarTransferencia(transferenciaRequestDTO));
        verify(transacaoDAO, never()).transferir(anyString(), anyString(), any(BigDecimal.class));
    }

    @Test
    void realizarTransferencia_saldoInsuficiente() {

        TransferenciaRequestDTO transferenciaRequestDTO = new TransferenciaRequestDTO("123456", "654321", BigDecimal.valueOf(150.00));
        Usuario contaOrigem = new Usuario();
        contaOrigem.setNumeroConta("123456");
        contaOrigem.setSaldo(BigDecimal.valueOf(100.00));

        Usuario contaDestino = new Usuario();
        contaDestino.setNumeroConta("654321");

        when(transacaoDAO.buscarPorNumeroConta(transferenciaRequestDTO.getNumeroContaOrigem())).thenReturn(Optional.of(contaOrigem));
        when(transacaoDAO.buscarPorNumeroConta(transferenciaRequestDTO.getNumeroContaDestino())).thenReturn(Optional.of(contaDestino));

        assertThrows(SaldoInsuficienteException.class, () -> transacaoService.realizarTransferencia(transferenciaRequestDTO));
        verify(transacaoDAO, never()).transferir(anyString(), anyString(), any(BigDecimal.class));
    }
}

