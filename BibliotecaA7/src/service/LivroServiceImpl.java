package service;

import dao.DaoFactory;
import model.Livro;
import dao.LivroDAO;
import java.util.List;

public class LivroServiceImpl implements LivroService {

    private final LivroDAO livroDAO;

    public LivroServiceImpl() {
        this.livroDAO = DaoFactory.getLivroDAO();
    }

    @Override
    public boolean save(Livro entity) {
        boolean b = false;

        if (entity != null) {
            b = this.livroDAO.save(entity);
        }

        return b;
    }

    @Override
    public boolean excluir(String isbn) {
        boolean b = false;

        if (isbn != null) {
            b = this.livroDAO.excluir(isbn);
        }

        return b;
    }

    @Override
    public boolean verificaId(long id) {
        boolean b = false;

        if (id > 0) {
            b = this.livroDAO.verificaId(id);
        }

        return b;
    }

    @Override
    public long recuperaUltimoId() {
        long b;
        b = this.livroDAO.recuperaUltimoId();
        return b;
    }

    @Override
    public List<Livro> recuperaLivro() {
        List<Livro> b;
        b = this.livroDAO.recuperaLivro();
        return b;
    }

    @Override
    public List<Livro> recuperaLivro(String chave) {
        List<Livro> b;

        b = this.livroDAO.recuperaLivro(chave);

        return b;
    }

    @Override
    public List<Livro> recuperaLivroPessoa(long idPessoa) {
        List<Livro> b;
        b = this.livroDAO.recuperaLivroPessoa(idPessoa);
        return b;
    }

    @Override
    public long recuperaIsbn(String isbn) {
        long b;
        b = this.livroDAO.recuperaIsbn(isbn);
        return b;
    }

    @Override
    public boolean update(Livro livro, long idPessoa) {
        boolean b;
        b = this.livroDAO.update(livro, idPessoa);
        return b;
    }

    @Override
    public boolean updateIsbn(Livro livro) {
        boolean b;
        b = this.livroDAO.updateIsbn(livro);
        return b;
    }

    @Override
    public long verifica(String pessoa) {
        long b;
        b = this.livroDAO.verifica(pessoa);
        return b;
    }
}
