package dao;

import model.Contato;

public interface ContatoDAO {

    final String INSERT_CONTATO = "INSERT INTO Contato(idContato, "
            + "telefoneResidencial, telefoneComercial, email, celular, Pessoa_idPessoa) "
            + "VALUES(?, ?, ?, ?, ?, ?)";

    final String RECUPERA_CONTATO = "SELECT * FROM contato WHERE idContato = ?";

    final String RECUPERA_CONTATO_PESSOA = "SELECT * FROM contato WHERE Pessoa_idPessoa = ?";

    final String RECUPERA_ID = "select Max(idContato) from contato";

    final String UPDATE = "UPDATE contato SET "
            + " telefoneResidencial = ?, telefoneComercial = ?, email = ?, "
            + "celular = ? WHERE Pessoa_idPessoa = ?";

    final String EXCLUIR = "DELETE FROM contato "
            + "WHERE idContato = ? ";

    public boolean save(Contato contato);

    public boolean exclui(long idContato);

    public boolean update(Contato contato);

    public Contato recuperaContato(long idContato);

    public long recuperaUltimoId();

    public Contato recuperaContatoPessoa(long idPessoa);

}
