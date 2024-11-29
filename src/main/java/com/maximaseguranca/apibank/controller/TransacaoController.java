package com.maximaseguranca.apibank.controller;

import com.maximaseguranca.apibank.dto.DepositoRequestDTO;
import com.maximaseguranca.apibank.dto.SaqueRequestDTO;
import com.maximaseguranca.apibank.dto.TransferenciaRequestDTO;
import com.maximaseguranca.apibank.service.TransacaoService;
import com.maximaseguranca.apibank.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transactions")
@Tag(name = "Transações", description = "Controle de Transações")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @Autowired
    UsuarioService usuarioService;

    @Operation(summary = "Realizar depósito")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Depósito realizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou valor de depósito inválido"),
            @ApiResponse(responseCode = "404", description = "Conta não encontrada")
    })
    @PostMapping("/deposits")
    public ResponseEntity<String> realizarDeposito(@RequestBody DepositoRequestDTO depositoRequestDTO) {
        transacaoService.realizarDeposito(depositoRequestDTO);
        return ResponseEntity.ok("Depósito realizado com sucesso.");
    }

    @Operation(summary = "Realizar saque")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Saque realizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos, valor de saque inválido e Saldo insuficiente"),
            @ApiResponse(responseCode = "404", description = "Conta não encontrada")
    })
    @PostMapping("/withdraw")
    public ResponseEntity<String> realizarSaque(@RequestBody SaqueRequestDTO saqueRequestDTO) {
        transacaoService.realizarSaque(saqueRequestDTO);
        return ResponseEntity.ok("Saque realizado com sucesso.");
    }

    @Operation(summary = "Realizar transferência")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transferência realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Saldo insuficiente ou dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Conta não encontrada")
    })
    @PostMapping
    public ResponseEntity<String> realizarTransferencia(@RequestBody TransferenciaRequestDTO transferenciaRequestDTO) {
        transacaoService.realizarTransferencia(transferenciaRequestDTO);
        return ResponseEntity.ok("Transferência realizada com sucesso.");
    }
}
