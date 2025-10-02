package br.com.biblioteca.biblioteca_api.service;

import br.com.biblioteca.biblioteca_api.model.Emprestimo;
import br.com.biblioteca.biblioteca_api.model.Livro;
import br.com.biblioteca.biblioteca_api.model.Usuario;
import br.com.biblioteca.biblioteca_api.repository.EmprestimoRepository;
import br.com.biblioteca.biblioteca_api.repository.LivroRepository;
import br.com.biblioteca.biblioteca_api.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Regras de negócio de empréstimo/devolução com garantia transacional.
 */
@Service
public class EmprestimoService {

    private final EmprestimoRepository emprestimos;
    private final UsuarioRepository usuarios;
    private final LivroRepository livros;

    public EmprestimoService(EmprestimoRepository e, UsuarioRepository u, LivroRepository l) {
        this.emprestimos = e; this.usuarios = u; this.livros = l;
    }

    public List<Emprestimo> listarAbertos() {
        return emprestimos.findByDataDevolucaoIsNull();
    }

    public List<Emprestimo> listarAtrasados() {
        return emprestimos.findByDataDevolucaoIsNullAndDataPrevistaBefore(LocalDate.now());
    }

    @Transactional
    public Emprestimo criar(Long usuarioId, Long livroId, LocalDate dataPrevista) {
        Usuario u = usuarios.findById(usuarioId).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        Livro l = livros.findById(livroId).orElseThrow(() -> new IllegalArgumentException("Livro não encontrado"));

        if (!l.podeEmprestar()) throw new IllegalStateException("Sem exemplares disponíveis");

        l.emprestar();
        livros.save(l);

        Emprestimo emp = new Emprestimo(u, l, dataPrevista);
        return emprestimos.save(emp);
    }

    @Transactional
    public Emprestimo devolver(Long emprestimoId) {
        Emprestimo emp = emprestimos.findById(emprestimoId)
                .orElseThrow(() -> new IllegalArgumentException("Empréstimo não encontrado"));

        if (emp.getDataDevolucao() != null) return emp;

        Livro l = emp.getLivro();
        l.devolver();
        livros.save(l);

        emp.setDataDevolucao(LocalDate.now());
        return emprestimos.save(emp);
    }
}
