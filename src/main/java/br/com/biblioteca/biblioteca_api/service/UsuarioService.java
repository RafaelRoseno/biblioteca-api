package br.com.biblioteca.biblioteca_api.service;

import br.com.biblioteca.biblioteca_api.model.Aluno;
import br.com.biblioteca.biblioteca_api.model.Professor;
import br.com.biblioteca.biblioteca_api.model.Usuario;
import br.com.biblioteca.biblioteca_api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/** Regras de negócio para usuários. */
@Service
public class UsuarioService {
    private final UsuarioRepository repo;
    public UsuarioService(UsuarioRepository repo) { this.repo = repo; }

    public List<Usuario> listar(String nome) {
        return (nome == null || nome.isBlank()) ? repo.findAll() : repo.findByNomeContainingIgnoreCase(nome);
    }
    public Usuario obter(Long id) { return repo.findById(id).orElse(null); }

    public Aluno criarAluno(Aluno a) { return repo.save(a); }
    public Professor criarProfessor(Professor p) { return repo.save(p); }

    public boolean excluir(Long id) {
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }
}
