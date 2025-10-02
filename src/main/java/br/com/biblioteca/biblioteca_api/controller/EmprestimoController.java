package br.com.biblioteca.biblioteca_api.controller;

import br.com.biblioteca.biblioteca_api.dto.NovoEmprestimoRequest;
import br.com.biblioteca.biblioteca_api.model.Emprestimo;
import br.com.biblioteca.biblioteca_api.service.EmprestimoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Endpoints REST para empréstimos e devoluções.
 */
@RestController
@RequestMapping("rest/v1/api/emprestimos")
public class EmprestimoController {

    private final EmprestimoService service;
    public EmprestimoController(EmprestimoService service) { this.service = service; }

    /** Lista empréstimos em aberto. */
    @GetMapping
    public List<Emprestimo> listarAbertos(@RequestParam(required = false) Boolean atrasados) {
        if (Boolean.TRUE.equals(atrasados)) return service.listarAtrasados();
        return service.listarAbertos();
    }

    /** Cria um novo empréstimo (decrementa estoque). */
    @PostMapping
    public ResponseEntity<?> criar(@RequestBody NovoEmprestimoRequest req) {
        try {
            Emprestimo salvo = service.criar(req.usuarioId, req.livroId, req.dataPrevista);
            return ResponseEntity.status(201).body(salvo);
        } catch (IllegalArgumentException | IllegalStateException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    /** Realiza a devolução (incrementa estoque). */
    @PostMapping("/{id}/devolucao")
    public ResponseEntity<?> devolver(@PathVariable Long id) {
        try {
            Emprestimo salvo = service.devolver(id);
            return ResponseEntity.ok(salvo);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
