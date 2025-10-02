package br.com.biblioteca.biblioteca_api.repository;

import br.com.biblioteca.biblioteca_api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/** Repositório JPA para a hierarquia de Usuario. */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findByNomeContainingIgnoreCase(String nome);
}
