package com.maximaseguranca.apibank.repository;

import com.maximaseguranca.apibank.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioDAO {
    void salvar(Usuario usuario);
    Optional<Usuario> buscarPorCpf(String cpf);
    Optional<Usuario> buscarPorId(Long id);
    List<Usuario> listarTodos();
}
