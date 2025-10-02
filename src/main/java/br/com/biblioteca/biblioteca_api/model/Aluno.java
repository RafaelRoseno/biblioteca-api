package br.com.biblioteca.biblioteca_api.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/** Usuário do tipo Aluno. */
@Entity
@DiscriminatorValue("ALUNO")
public class Aluno extends Usuario {
    public Aluno() { }
    public Aluno(String nome, String ra) { super(nome, ra); }
}
