package com.maximaseguranca.apibank;

import com.maximaseguranca.apibank.dto.UsuarioRequestDTO;
import com.maximaseguranca.apibank.dto.UsuarioResponseDTO;
import com.maximaseguranca.apibank.exception.CpfJaCadastradoException;
import com.maximaseguranca.apibank.exception.UsuarioMenorDeIdadeException;
import com.maximaseguranca.apibank.exception.UsuarioNaoEncontradoException;
import com.maximaseguranca.apibank.model.Usuario;
import com.maximaseguranca.apibank.repository.UsuarioDAO;
import com.maximaseguranca.apibank.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioDAO usuarioDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void cadastrarUsuario_comSucesso() {

        UsuarioRequestDTO requestDTO = new UsuarioRequestDTO("João", 20, "12345678900");
        when(usuarioDAO.buscarPorCpf(requestDTO.getCpf())).thenReturn(Optional.empty());

        UsuarioResponseDTO responseDTO = usuarioService.cadastrarUsuario(requestDTO);

        assertNotNull(responseDTO);
        assertEquals("João", responseDTO.getNome());
        verify(usuarioDAO, times(1)).salvar(any(Usuario.class));
    }

    @Test
    void cadastrarUsuario_usuarioMenorDeIdade() {

        UsuarioRequestDTO requestDTO = new UsuarioRequestDTO("João", 16, "12345678900");

        assertThrows(UsuarioMenorDeIdadeException.class, () -> usuarioService.cadastrarUsuario(requestDTO));
        verify(usuarioDAO, never()).salvar(any(Usuario.class));
    }

    @Test
    void cadastrarUsuario_cpfJaCadastrado() {

        UsuarioRequestDTO requestDTO = new UsuarioRequestDTO("João", 20, "12345678900");
        when(usuarioDAO.buscarPorCpf(requestDTO.getCpf())).thenReturn(Optional.of(new Usuario()));

        assertThrows(CpfJaCadastradoException.class, () -> usuarioService.cadastrarUsuario(requestDTO));
        verify(usuarioDAO, never()).salvar(any(Usuario.class));
    }

    @Test
    void buscarPorId_comSucesso() {

        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("João");
        usuario.setIdade(20);
        usuario.setCpf("12345678900");
        when(usuarioDAO.buscarPorId(1L)).thenReturn(Optional.of(usuario));

        Optional<UsuarioResponseDTO> responseDTO = usuarioService.buscarPorId(1L);

        assertTrue(responseDTO.isPresent());
        assertEquals("João", responseDTO.get().getNome());
        verify(usuarioDAO, times(1)).buscarPorId(1L);
    }

    @Test
    void buscarPorId_usuarioNaoEncontrado() {

        when(usuarioDAO.buscarPorId(1L)).thenReturn(Optional.empty());

        assertThrows(UsuarioNaoEncontradoException.class, () -> usuarioService.buscarPorId(1L));
        verify(usuarioDAO, times(1)).buscarPorId(1L);
    }

    @Test
    void listarTodos_comSucesso() {

        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("João");
        usuario.setIdade(20);
        usuario.setCpf("12345678900");
        when(usuarioDAO.listarTodos()).thenReturn(Collections.singletonList(usuario));

        var usuarios = usuarioService.listarTodos();

        assertFalse(usuarios.isEmpty());
        assertEquals(1, usuarios.size());
        assertEquals("João", usuarios.get(0).getNome());
        verify(usuarioDAO, times(1)).listarTodos();
    }
}

