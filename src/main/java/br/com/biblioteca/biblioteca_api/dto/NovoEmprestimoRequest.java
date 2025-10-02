package br.com.biblioteca.biblioteca_api.dto;

import java.time.LocalDate;

/** Payload para criar empréstimo. */
public class NovoEmprestimoRequest {
    public Long usuarioId;
    public Long livroId;
    public LocalDate dataPrevista;
}
