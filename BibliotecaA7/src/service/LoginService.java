package service;

import model.Login;

public interface LoginService {

    boolean save(Login login);

    long recuperaUltimoId();

    int consultaLogin(String usuario, String senha);

    /**
     * 0 - nao cadastrado 1 - usuario comun 2 - adm
     */
    boolean verificaUsuario(String usuario);

    String consultaLoginUsuario(long idPessoa);

    long consultaLongUsuario(long idPessoa);

    boolean update(Login login, long idOld);

    boolean exclui(long idLogin);
}
