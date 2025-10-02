package br.com.biblioteca.biblioteca_api.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/** Usuário do tipo Professor. */
@Entity
@DiscriminatorValue("PROFESSOR")
public class Professor extends Usuario {
    public Professor() { }
    public Professor(String nome, String matricula) { super(nome, matricula); }
}
