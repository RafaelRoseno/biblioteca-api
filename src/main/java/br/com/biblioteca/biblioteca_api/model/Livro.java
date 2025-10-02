package br.com.biblioteca.biblioteca_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 * Entidade que representa um Livro no sistema de biblioteca.
 * Contém informações básicas como título, autor, quantidade total e quantidade disponível.
 */
@Setter
@Getter
@Entity
public class Livro {
    /** Identificador único do livro (chave primária). */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Título do livro. */
    private String titulo;

    /** Autor do livro. */
    private String autor;

    /** Quantidade total de exemplares cadastrados. */
    private int totais;

    /** Quantidade de exemplares disponíveis para empréstimo. */
    private int disponiveis;

    public Livro() {}
    public Livro(String titulo, String autor, int totais) {
        this.titulo=titulo; this.autor=autor; this.totais=totais; this.disponiveis=totais;
    }

    /**
     * Verifica se ainda há exemplares disponíveis.
     * @return true se houver exemplares para empréstimo, false caso contrário
     */
    public boolean podeEmprestar() { return disponiveis > 0; }

    /** Decrementa um exemplar disponível (em caso de empréstimo). */
    public void emprestar() { if (podeEmprestar()) disponiveis--; }

    /** Incrementa um exemplar disponível (em caso de devolução). */
    public void devolver() { if (disponiveis < totais) disponiveis++; }

}
