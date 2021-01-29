package dao;

import java.util.List;
import model.Pessoa;

public interface PessoaDAO {

    final String INSERT_PESSOA = "INSERT INTO Pessoa(idPessoa, nome, "
            + "idade, dataNascimento, cpf, sexo, Login_idLogin ) "
            + "VALUES(?, ?, ?, ?, ?, ?, ? )";

    final String RECUPERA_ID = "select Max(idPessoa) from pessoa";

    final String RECUPERA_PESSOA = "SELECT * FROM pessoa WHERE idPessoa = ? ";

    final String RECUPERA = "SELECT * FROM pessoa ";

    final String VERIFICA_CPF = "SELECT * FROM pessoa WHERE cpf = ?";

    final String UPDATE = "UPDATE pessoa SET "
            + "nome = ?, idade = ?, dataNascimento = ?, "
            + "cpf = ?, sexo = ? "
            + "WHERE idPessoa = ? ";

    final String EXCLUIR = "DELETE FROM pessoa "
            + "WHERE idPessoa = ? ";

    public boolean save(Pessoa pessoa);

    public boolean exclui(long idPessoa);

    public Pessoa recuperaPessoaId(long idpessoa);

    public long recuperaUltimoId();

    public List<Pessoa> recuperaPessoa();

    public boolean verificaCPF(String cpf);

    public long idCPF(String cpf);

    public boolean update(Pessoa pessoa, long idOld);
}
