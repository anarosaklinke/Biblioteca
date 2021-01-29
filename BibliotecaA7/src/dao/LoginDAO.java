package dao;

import model.Login;

public interface LoginDAO {

    final String INSERT_LOGIN = "INSERT INTO Login(idLogin, "
            + "usuario, senha, admin) VALUES (?, ?, ?, ?)";
    final String RECUPERA_ID = "select Max(idLogin) from login";
    final String CONSULTA_LOGIN = "SELECT * FROM login where usuario = ?";
    final String LOGIN_USUARIO = "SELECT usuario FROM login "
            + "INNER JOIN pessoa "
            + "ON pessoa.idPessoa = ? AND login.idLogin = pessoa.Login_idLogin  ";

    final String IDLOGIN_USUARIO = "SELECT idLogin FROM login "
            + "INNER JOIN pessoa "
            + "ON pessoa.idPessoa = ? AND login.idLogin = pessoa.Login_idLogin  ";

    final String UPDATE = "UPDATE login SET "
            + "usuario = ?, senha = ? "
            + "WHERE idLogin = ? ";

    final String EXCLUIR = " DELETE FROM login WHERE (idLogin = ?)";

    public boolean save(Login login);

    public boolean exclui(long idLogin);

    public long recuperaUltimoId();

    public int consultaLogin(String usuario, String senha);

    public String consultaLoginUsuario(long idPessoa);

    public boolean verificaUsuario(String usuario);

    public long consultaLongUsuario(long idPessoa);

    public boolean update(Login login, long idOld);
}
