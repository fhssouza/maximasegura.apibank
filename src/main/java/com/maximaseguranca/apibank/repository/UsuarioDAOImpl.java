package com.maximaseguranca.apibank.repository;

import com.maximaseguranca.apibank.model.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioDAOImpl implements UsuarioDAO {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void salvar(Usuario usuario) {
        entityManager.persist(usuario);
    }

    @Override
    public Optional<Usuario> buscarPorCpf(String cpf) {
        String sql = "SELECT u FROM Usuario u WHERE u.cpf = :cpf";
        try {
            Usuario usuario = entityManager.createQuery(sql, Usuario.class)
                    .setParameter("cpf", cpf)
                    .getSingleResult();
            return Optional.of(usuario);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }


    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        String sql = "SELECT * FROM usuarios WHERE id = :id";
        try {
            List<Usuario> usuarios = entityManager.createNativeQuery(sql, Usuario.class)
                    .setParameter("id", id)
                    .getResultList();
            if (usuarios.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(usuarios.get(0));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Usuario> listarTodos() {
        String sql = "SELECT * FROM usuarios";
        return entityManager.createNativeQuery(sql, Usuario.class)
                .getResultList();
    }

}