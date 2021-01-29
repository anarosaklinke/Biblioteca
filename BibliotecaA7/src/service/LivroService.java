package service;

import java.util.List;
import model.Livro;

public interface LivroService {

    boolean save(Livro livro);

    boolean verificaId(long id);

    long recuperaUltimoId();

    List<Livro> recuperaLivroPessoa(long idPessoa);

    long recuperaIsbn(String isbn);

    List<Livro> recuperaLivro();

    List<Livro> recuperaLivro(String chave);

    boolean update(Livro livro, long idPessoa);

    boolean updateIsbn(Livro livro);

    long verifica(String pessoa);

    boolean excluir(String isbn);
}
