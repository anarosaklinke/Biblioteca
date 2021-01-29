package dao;

import model.Endereco;

public interface EnderecoDAO {

    final String INSERT_ENDERECO = "INSERT INTO Endereco(idEndereco, rua, "
            + "numero, bairro, cidade, estado, pais, cep, Pessoa_idPessoa) "
            + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    final String RECUPERA_ID = "select Max(idEndereco) from endereco";
    final String RECUPERA_ENDERECO_PESSOA = "SELECT * FROM endereco WHERE Pessoa_idPessoa = ?";

    final String UPDATE = "UPDATE endereco SET "
            + "rua = ?, numero = ?, bairro = ?, cidade = ?, estado = ?, pais = ?, "
            + "cep = ? WHERE Pessoa_idPessoa = ?";

    final String EXCLUIR = "DELETE FROM endereco "
            + "WHERE idEndereco = ? ";

    public boolean save(Endereco endereco);

    public boolean exclui(long idEndereco);

    public boolean update(Endereco endereco);

    public long recuperaUltimoId();

    public Endereco recuperaEnderecoPessoa(long idPessoa);
}
