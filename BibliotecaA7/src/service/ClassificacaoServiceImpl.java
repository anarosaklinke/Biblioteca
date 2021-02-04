package service;

import dao.DaoFactory;
import dao.ClassificacaoDAO;
import java.util.List;
import model.Classificacao;

public class ClassificacaoServiceImpl implements ClassificacaoService {

    private final ClassificacaoDAO classificacaoDAO;

    public ClassificacaoServiceImpl() {
        this.classificacaoDAO = DaoFactory.getClassificacaoDAO();
    }

    @Override
    public boolean save(Classificacao classificacao) {
        boolean b = false;

        if (classificacao != null) {
            b = this.classificacaoDAO.save(classificacao);
        }

        return b;
    }

    @Override
    public boolean excluir(String nome) {
        boolean b = false;
        if (nome != null) {
            b = this.classificacaoDAO.excluir(nome);
        }
        return b;
    }

    @Override
    public long recuperaUltimoId() {
        long id;
        id = this.classificacaoDAO.recuperaUltimoId();
        return id;
    }

    @Override
    public long verifica(String nome) {
        long result = -1;
        if (nome != null) {
            result = this.classificacaoDAO.verifica(nome);
        }
        return result;
    }

    @Override
    public boolean verificaExcluir(long idClassificacao) {
        boolean result = false;
        if (idClassificacao > 0) {
            result = this.classificacaoDAO.verificaExcluir(idClassificacao);
        }
        return result;
    }

    @Override
    public String verificaNome(long id) {
        String result = null;

        if (id > 0) {
            result = this.classificacaoDAO.verificaNome(id);

        }
        return result;
    }

    @Override
    public boolean update(String nome, long idOld) {
        boolean b = false;

        if (nome != null) {
            if (idOld > 0) {
                b = this.classificacaoDAO.update(nome, idOld);
            }
        }

        return b;
    }

    @Override
    public List<Classificacao> recuperaClassificacao() {
        List<Classificacao> b;

        b = this.classificacaoDAO.recuperaClassificacao();

        return b;
    }

}
