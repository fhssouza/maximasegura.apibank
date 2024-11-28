package com.maximaseguranca.apibank.service;

import com.maximaseguranca.apibank.dto.UsuarioDepositoRequestDTO;
import com.maximaseguranca.apibank.dto.UsuarioRequestDTO;
import com.maximaseguranca.apibank.dto.UsuarioResponseDTO;
import com.maximaseguranca.apibank.exception.*;
import com.maximaseguranca.apibank.model.Usuario;
import com.maximaseguranca.apibank.repository.UsuarioDAO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Transactional
    public UsuarioResponseDTO cadastrarUsuario(UsuarioRequestDTO usuarioRequestDTO) {
        if (usuarioRequestDTO.getIdade() < 18){
            throw new UsuarioMenorDeIdadeException("Usuário deve ter 18 anos ou mais.");
        }

        if (usuarioDAO.buscarPorCpf(usuarioRequestDTO.getCpf()).isPresent()){
            throw new CpfJaCadastradoException("CPF já cadastrado.");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(usuarioRequestDTO.getNome());
        usuario.setIdade(usuarioRequestDTO.getIdade());
        usuario.setCpf(usuarioRequestDTO.getCpf());

        usuarioDAO.salvar(usuario);

        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getIdade(),
                usuario.getCpf(),
                usuario.getNumeroConta(),
                usuario.getSaldo()
        );
    }

    public Optional<UsuarioResponseDTO> buscarPorId(Long id) {
        Optional<Usuario> usuarioOptional = usuarioDAO.buscarPorId(id);
        if (usuarioOptional.isEmpty()) {
            throw new UsuarioNaoEncontradoException("Usuário com id " + id + " não encontrado.");
        }
        Usuario usuario = usuarioOptional.get();

        UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getIdade(),
                usuario.getCpf(),
                usuario.getNumeroConta(),
                usuario.getSaldo()
        );

        return Optional.of(usuarioResponseDTO);
    }

    public List<UsuarioResponseDTO> listarTodos() {
        List<Usuario> usuarios = usuarioDAO.listarTodos();
        return usuarios.stream().map(usuario -> new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getIdade(),
                usuario.getCpf(),
                usuario.getNumeroConta(),
                usuario.getSaldo()
        )).collect(Collectors.toList());
    }

    @Transactional
    public void realizarDeposito(UsuarioDepositoRequestDTO depositoRequestDTO) {

        if (depositoRequestDTO.getValor().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValorInvalidoException("O valor do depósito deve ser maior que 0.00.");
        }

        Optional<Usuario> usuarioOptional = usuarioDAO.buscarPorNumeroConta(depositoRequestDTO.getNumeroConta());

        if (usuarioOptional.isEmpty()) {
            throw new ContaNaoEncontradaException("Conta com número " + depositoRequestDTO.getNumeroConta() + " não encontrada.");
        }

        Usuario deposito = new Usuario();
        deposito.setNumeroConta(depositoRequestDTO.getNumeroConta());
        deposito.setSaldo(depositoRequestDTO.getValor());

        usuarioDAO.realizarDeposito(deposito);
    }
}
