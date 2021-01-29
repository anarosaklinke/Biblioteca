package service;

public class ServiceFactory {
    private ServiceFactory(){
    }    
   
    public static PessoaService getPessoaService(){
        return new PessoaServiceImpl();
    }
    
    public static ContatoService getContatoService(){
        return new ContatoServiceImpl();
    }
    
    public static EnderecoService getEnderecoService(){
        return new EnderecoServiceImpl();
    }
    
    public static LoginService getLoginService(){
        return new LoginServiceImpl();
    }
    
    public static LivroService getLivroService(){
        return new LivroServiceImpl();
    }       
}
