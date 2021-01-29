package dao;

import java.util.List;
import model.Livro;

public interface LivroDAO {

    final String INSERT = "INSERT INTO Livro("
            + "idLivro, dataPublicacao, isbn, autores, semelhantes, titulo, "
            + "Pessoa_idPessoa, Classificacao_idClassificacao) "
            + "VALUES (?, ?, ?, ?, ?, ? ,?, ?)";

    final String RECUPERA_ID = "select Max(idLivro) from livro";

    final String VERIFICA_ID = "SELECT * FROM livro WHERE idLivro = ?";

    final String VERIFICA_ISBN = "SELECT * FROM livro WHERE isbn = ?";
    final String CONSULTA_LIVRO = "SELECT * FROM livro";

    final String LIVRO_PESSOA = "SELECT * FROM livro WHERE "
            + "Pessoa_idPessoa = ?";

    final String UPDATE = "UPDATE Livro SET "
            + "titulo = ?, Classificacao_idClassificacao = ?, "
            + "dataPublicacao = ?, autores = ?, Pessoa_idPessoa = ? "
            + "WHERE idLivro = ? ";
    
    final String UPDATE_S = "UPDATE Livro SET "
            + "titulo = ?, semelhantes = ?, Classificacao_idClassificacao = ?, "
            + "dataPublicacao = ?, autores = ?, Pessoa_idPessoa = ? "
            + "WHERE idLivro = ? ";

    final String VERIFICA = "SELECT idPessoa FROM Pessoa "
            + "INNER JOIN login "
            + "ON login.usuario = ? AND pessoa.Login_idLogin = login.idLogin ";

    final String EXCLUIR = "DELETE FROM livro "
            + "WHERE isbn = ? ";

    final String UPDATE_ISBN_S = "UPDATE Livro SET "
            + "titulo = ?, semelhantes = ?, Classificacao_idClassificacao = ?, "
            + " dataPublicacao = ?, autores = ? "
            + "WHERE isbn = ? ";
    
    final String UPDATE_ISBN = "UPDATE Livro SET "
            + "titulo = ?, Classificacao_idClassificacao = ?, "
            + " dataPublicacao = ?, autores = ? "
            + "WHERE isbn = ? ";
    
    final String CHAVE = "SELECT * FROM livro "
            + "INNER JOIN classificacao ON"
            + "(dataPublicacao LIKE ? OR "
            + "isbn LIKE ? OR "
            + "autores LIKE ? OR "
            + "semelhantes LIKE ? OR "
            + "titulo LIKE ? OR "
            + "classificacao.nome LIKE ? ) AND "
            + "livro.Classificacao_idClassificacao  = classificacao.idClassificacao ";

    public boolean save(Livro livro);

    public boolean verificaId(long id);

    public long recuperaUltimoId();

    public List<Livro> recuperaLivroPessoa(long idPessoa);

    public List<Livro> recuperaLivro();

    public List<Livro> recuperaLivro(String chave);

    public long recuperaIsbn(String isbn);

    public boolean update(Livro livro, long idPessoa);

    public boolean updateIsbn(Livro livro);

    public long verifica(String pessoa);

    public boolean excluir(String isbn);
}
