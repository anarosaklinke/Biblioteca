package dao;

public class DaoFactory {



    private DaoFactory() {
    }

    public static LoginDAO getLoginDAO() {
        return new LoginDAOImpl();
    }
    
    public static ClassificacaoDAO getClassificacaoDAO() {
        return new ClassificacaoDAOImpl();
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

    public static LivroDAO getLivroDAO() {
        return new LivroDAOImpl();
    }

}
