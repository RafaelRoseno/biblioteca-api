package br.com.biblioteca.biblioteca_api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Entidade que representa o empréstimo de um livro por um usuário.
 * Regras: ao emprestar, decrementa 'disponiveis' do livro; ao devolver, incrementa.
 */
@Getter
@Entity
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Usuário que realiza o empréstimo (Aluno/Professor). */
    @ManyToOne(optional = false)
    private Usuario usuario;

    /** Livro emprestado. */
    @ManyToOne(optional = false)
    private Livro livro;

    private LocalDate dataEmprestimo;
    private LocalDate dataPrevista;
    @Setter
    private LocalDate dataDevolucao;

    public Emprestimo() { }
    public Emprestimo(Usuario u, Livro l, LocalDate prevista) {
        this.usuario = u;
        this.livro = l;
        this.dataEmprestimo = LocalDate.now();
        this.dataPrevista = prevista;
    }

    public boolean atrasado() {
        return dataDevolucao == null && LocalDate.now().isAfter(dataPrevista);
    }

}
