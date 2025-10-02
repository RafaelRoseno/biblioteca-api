package br.com.biblioteca.biblioteca_api.repository;

import br.com.biblioteca.biblioteca_api.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório JPA para a entidade Livro.
 * Fornece operações básicas de CRUD sem necessidade de implementação manual.
 */
@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {}
