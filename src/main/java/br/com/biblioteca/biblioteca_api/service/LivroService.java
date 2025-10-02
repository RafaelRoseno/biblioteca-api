package br.com.biblioteca.biblioteca_api.service;

import br.com.biblioteca.biblioteca_api.model.Livro;
import br.com.biblioteca.biblioteca_api.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Camada de serviço para manipulação da entidade Livro.
 * Contém regras de negócio simples para criação, atualização e exclusão de livros.
 */
@Service
public class LivroService {
    private final LivroRepository repo;

    /**
     * Injeta o repositório de livros no serviço.
     * @param repo repositório de livros
     */
    public LivroService(LivroRepository repo){ this.repo = repo; }

    /**
     * Lista todos os livros cadastrados.
     * @return lista de livros
     */
    public List<Livro> listar(){ return repo.findAll(); }

    /**
     * Busca um livro pelo ID.
     * @param id identificador do livro
     * @return livro encontrado ou null
     */
    public Livro obter(Long id){ return repo.findById(id).orElse(null); }

    /**
     * Cria um novo livro no sistema.
     * @param l entidade Livro
     * @return livro salvo no banco de dados
     */
    public Livro criar(Livro l){
        if (l.getDisponiveis()==0) l.setDisponiveis(l.getTotais());
        return repo.save(l);
    }

    /**
     * Atualiza um livro existente.
     * @param id identificador do livro
     * @param l objeto livro atualizado
     * @return true se atualizado, false se não encontrado
     */
    public Livro atualizar(Long id, Livro l){
        return repo.findById(id)
                .map(existente -> {
                    existente.setTitulo(l.getTitulo());
                    existente.setAutor(l.getAutor());
                    existente.setTotais(l.getTotais());
                    existente.setDisponiveis(l.getDisponiveis());
                    return repo.save(existente);
                })
                .orElse(null);
    }

    /**
     * Exclui um livro pelo ID.
     * @param id identificador do livro
     * @return true se removido, false se não encontrado
     */
    public boolean excluir(Long id){
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }
}
