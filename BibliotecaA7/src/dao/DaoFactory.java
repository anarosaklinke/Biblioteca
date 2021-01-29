package dao;

public class DaoFactory {

    private DaoFactory() {
    }

    public static LoginDAO getLoginDAO() {
        return new LoginDAOImpl();
    }

    public static ContatoDAO getContatoDAO() {
        return new ContatoDAOImpl();
    }

    public static EnderecoDAO getEnderecoDAO() {
        return new EnderecoDAOImpl();
    }

    public static PessoaDAO getClienteDAO() {
        return new PessoaDAOImpl();
    }

    public static LivroDAO getOcorrenciasDAO() {
        return new LivroDAOImpl();
    }

}
