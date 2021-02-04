package dao;

import java.util.List;
import model.Classificacao;

public interface ClassificacaoDAO {

    final String INSERT = "INSERT INTO Classificacao("
            + "idClassificacao, nome )"
            + "VALUES (?, ?)";

    final String RECUPERA_ID = "select Max(idClassificacao) from Classificacao";

    final String VERIFICA_NOME = "SELECT * FROM Classificacao WHERE nome = ?";

    final String VERIFICA_NOME_ID = "SELECT nome FROM Classificacao WHERE idClassificacao = ? ";

    final String RECUPERA_CLASSIFICACAO = "SELECT * FROM Classificacao";

    final String UPDATE = "UPDATE Classificacao SET "
            + "nome = ? "
            + "WHERE idClassificacao = ? ";

    final String VERIFICA = "SELECT * FROM livro WHERE "
            + " Classificacao_idClassificacao = ? ";

    final String EXCLUIR = "DELETE FROM Classificacao "
            + "WHERE nome = ? ";

    public boolean save(Classificacao classificacao);

    public boolean excluir(String nome);

    public long verifica(String nome);

    public String verificaNome(long id);

    public long recuperaUltimoId();

    public boolean update(String nome, long idOld);

    public List<Classificacao> recuperaClassificacao();

    public boolean verificaExcluir(long idClassificacao);
}
