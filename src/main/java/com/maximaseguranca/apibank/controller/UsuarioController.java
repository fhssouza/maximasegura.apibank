package com.maximaseguranca.apibank.controller;

import com.maximaseguranca.apibank.dto.UsuarioConsultaResponseDTO;
import com.maximaseguranca.apibank.dto.UsuarioRequestDTO;
import com.maximaseguranca.apibank.dto.UsuarioResponseDTO;
import com.maximaseguranca.apibank.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> cadastrarUsuario(@RequestBody @Valid UsuarioRequestDTO usuarioRequestDTO) {
        UsuarioResponseDTO usuarioResponseDTO = usuarioService.cadastrarUsuario(usuarioRequestDTO);
        return new ResponseEntity<>(usuarioResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarUsuarioPorId(@PathVariable Long id) {
        Optional<UsuarioConsultaResponseDTO> usuarioDTO = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(usuarioDTO.get());
    }
}
