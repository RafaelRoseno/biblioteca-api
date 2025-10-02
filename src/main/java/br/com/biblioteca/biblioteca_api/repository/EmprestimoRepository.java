package br.com.biblioteca.biblioteca_api.repository;

import br.com.biblioteca.biblioteca_api.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/** Repositório JPA para Emprestimo. */
@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    List<Emprestimo> findByDataDevolucaoIsNull();
    List<Emprestimo> findByDataDevolucaoIsNullAndDataPrevistaBefore(java.time.LocalDate data);
}
