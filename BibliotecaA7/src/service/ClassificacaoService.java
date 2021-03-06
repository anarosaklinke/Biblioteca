package service;

import java.util.List;
import model.Classificacao;

public interface ClassificacaoService {

    boolean save(Classificacao classificacao);

    boolean excluir(String nome);

    long verifica(String nome);

    long recuperaUltimoId();

    boolean update(String nome, long idOld);

    List<Classificacao> recuperaClassificacao();

    String verificaNome(long id);

    boolean verificaExcluir(long idClassificacao);
}
