package service;

import dao.DaoFactory;
import dao.LoginDAO;
import model.Login;

public class LoginServiceImpl implements LoginService {

    private final LoginDAO loginDAO;

    public LoginServiceImpl() {
        this.loginDAO = DaoFactory.getLoginDAO();
    }

    @Override
    public boolean save(Login entity) {
        boolean b = false;

        if (entity != null) {
            b = this.loginDAO.save(entity);
        }

        return b;
    }

    @Override
    public boolean exclui(long idLogin) {
        boolean b = false;

        if (idLogin > 0) {
            b = this.loginDAO.exclui(idLogin);
        }

        return b;
    }

    @Override
    public boolean update(Login login, long idOld) {
        boolean b = false;

        if (login != null) {
            if (idOld > 0) {
                b = this.loginDAO.update(login, idOld);
            }
        }

        return b;
    }

    @Override
    public long recuperaUltimoId() {
        long id;
        id = this.loginDAO.recuperaUltimoId();
        return id;
    }

    @Override
    public int consultaLogin(String usuario, String senha) {
        int result = 0;

        if (usuario != null && senha != null) {
            result = this.loginDAO.consultaLogin(usuario, senha);
        }
        return result;
    }

    @Override
    public boolean verificaUsuario(String usuario) {
        boolean result = false;
        if (usuario != null) {
            result = this.loginDAO.verificaUsuario(usuario);
        }
        return result;
    }

    @Override
    public String consultaLoginUsuario(long idPessoa) {
        String result = null;
        if (idPessoa > 0) {
            result = this.loginDAO.consultaLoginUsuario(idPessoa);
        }
        return result;
    }

    @Override
    public long consultaLongUsuario(long idPessoa) {
        long result = -1;
        if (idPessoa > 0) {
            result = this.loginDAO.consultaLongUsuario(idPessoa);
        }
        return result;
    }
}
