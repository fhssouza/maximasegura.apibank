package com.maximaseguranca.apibank.controller;

import com.maximaseguranca.apibank.dto.UsuarioRequestDTO;
import com.maximaseguranca.apibank.dto.UsuarioResponseDTO;
import com.maximaseguranca.apibank.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Usuários", description = "Controle de Usuários")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @Operation(summary = "Cadastrar um novo usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou CPF já cadastrado")
    })
    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> cadastrarUsuario(@RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        UsuarioResponseDTO usuarioResponseDTO = usuarioService.cadastrarUsuario(usuarioRequestDTO);
        return new ResponseEntity<>(usuarioResponseDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "Buscar um usuário por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarUsuarioPorId(@PathVariable Long id) {
        Optional<UsuarioResponseDTO> usuarioDTO = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(usuarioDTO.get());
    }

    @Operation(summary = "Listar todos os usuários")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso")
    })
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarTodosUsuarios() {
        List<UsuarioResponseDTO> usuariosDTO = usuarioService.listarTodos();
        return ResponseEntity.ok(usuariosDTO);
    }


}
