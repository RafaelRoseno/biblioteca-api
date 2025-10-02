package br.com.biblioteca.biblioteca_api.controller;

import br.com.biblioteca.biblioteca_api.model.Aluno;
import br.com.biblioteca.biblioteca_api.model.Professor;
import br.com.biblioteca.biblioteca_api.model.Usuario;
import br.com.biblioteca.biblioteca_api.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Endpoints REST para gerenciamento de usuários (Aluno/Professor).
 */
@RestController
@RequestMapping("rest/v1/api/usuarios")
public class UsuarioController {

    private final UsuarioService service;
    public UsuarioController(UsuarioService service) { this.service = service; }

    /** Lista usuários (opcional: filtra por nome). */
    @GetMapping
    public List<Usuario> listar(@RequestParam(required = false) String nome) {
        return service.listar(nome);
    }

    /** Detalha um usuário por ID. */
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obter(@PathVariable Long id) {
        var u = service.obter(id);
        return (u == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(u);
    }

    /** Cria Aluno. */
    @PostMapping("/alunos")
    public ResponseEntity<Aluno> criarAluno(@RequestBody Aluno aluno) {
        var salvo = service.criarAluno(aluno);
        return ResponseEntity.status(201).body(salvo);
    }

    /** Cria Professor. */
    @PostMapping("/professores")
    public ResponseEntity<Professor> criarProfessor(@RequestBody Professor professor) {
        var salvo = service.criarProfessor(professor);
        return ResponseEntity.status(201).body(salvo);
    }

    /** Exclui usuário (Aluno/Professor). */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        return service.excluir(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
