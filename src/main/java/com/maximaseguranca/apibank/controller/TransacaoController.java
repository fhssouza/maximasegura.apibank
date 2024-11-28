package com.maximaseguranca.apibank.controller;

import com.maximaseguranca.apibank.dto.TransferenciaRequestDTO;
import com.maximaseguranca.apibank.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transactions")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @PostMapping
    public ResponseEntity<String> realizarTransferencia(@RequestBody TransferenciaRequestDTO transferenciaRequestDTO) {
        transacaoService.realizarTransferencia(transferenciaRequestDTO);
        return ResponseEntity.ok("Transferência realizada com sucesso.");
    }
}
