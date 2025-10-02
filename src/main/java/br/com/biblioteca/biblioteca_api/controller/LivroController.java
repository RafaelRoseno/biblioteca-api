package br.com.biblioteca.biblioteca_api.controller;

import br.com.biblioteca.biblioteca_api.model.Livro;
import br.com.biblioteca.biblioteca_api.service.LivroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST responsável por expor endpoints relacionados a Livros.
 * Permite operações CRUD via HTTP.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("rest/v1/api/livros")
public class LivroController {
    private final LivroService service;

    /**
     * Lista todos os livros.
     * @return lista de livros
     */
    @GetMapping
    public List<Livro> listar(){ return service.listar(); }

    /**
     * Obtém os detalhes de um livro pelo ID.
     * @param id identificador do livro
     * @return livro encontrado ou 404 se não encontrado
     */
    @GetMapping("/{id}")
    public ResponseEntity<Livro> obter(@PathVariable Long id){
        var l = service.obter(id);
        return (l==null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(l);
    }

    /**
     * Cria um novo livro no sistema.
     * @param livro objeto Livro enviado no corpo da requisição
     * @return livro salvo (HTTP 201)
     */
    @PostMapping
    public ResponseEntity<Livro> criar(@RequestBody Livro livro){
        var salvo = service.criar(livro);
        return ResponseEntity.status(201).body(salvo);
    }

    /**
     * Atualiza um livro existente.
     * @param id identificador do livro
     * @param livro dados do livro atualizado
     * @return HTTP 204 se atualizado, 404 se não encontrado
     */
    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizar(@PathVariable Long id, @RequestBody Livro livro){
        var atualizado = service.atualizar(id, livro);
        return (atualizado == null)
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(atualizado); // 200 + JSON do livro atualizado
    }

    /**
     * Exclui um livro existente.
     * @param id identificador do livro
     * @return HTTP 204 se excluído, 404 se não encontrado
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        return service.excluir(id) ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
