package com.maximaseguranca.apibank.service;

import com.maximaseguranca.apibank.dto.UsuarioRequestDTO;
import com.maximaseguranca.apibank.dto.UsuarioResponseDTO;
import com.maximaseguranca.apibank.exception.CpfJaCadastradoException;
import com.maximaseguranca.apibank.exception.UsuarioMenorDeIdadeException;
import com.maximaseguranca.apibank.model.Usuario;
import com.maximaseguranca.apibank.repository.UsuarioDAO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
