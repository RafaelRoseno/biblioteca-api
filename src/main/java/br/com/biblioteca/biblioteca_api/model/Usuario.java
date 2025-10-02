package br.com.biblioteca.biblioteca_api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Entidade abstrata base para usuários do sistema.
 * Utiliza herança SINGLE_TABLE com discriminador para Aluno/Professor.
 */
@Getter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_usuario")
public abstract class Usuario {

    /** Identificador do usuário. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Nome completo. */
    @Setter
    private String nome;

    /** Documento (RA para aluno, matrícula para professor, etc.). */
    @Setter
    private String documento;

    protected Usuario() { }
    protected Usuario(String nome, String documento) {
        this.nome = nome;
        this.documento = documento;
    }

}
