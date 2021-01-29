package service;

import dao.DaoFactory;
import dao.ContatoDAO;
import model.Contato;

public class ContatoServiceImpl implements ContatoService {

    private final ContatoDAO contatoDAO;

    public ContatoServiceImpl() {
        this.contatoDAO = DaoFactory.getContatoDAO();
    }

    @Override
    public boolean save(Contato entity) {
        boolean b = false;

        if (entity != null) {
            b = this.contatoDAO.save(entity);
        }

        return b;
    }
    
        @Override
    public boolean exclui(long idContato) {
        boolean b = false;

        if (idContato > 0) {
            b = this.contatoDAO.exclui(idContato);
        }

        return b;
    }
    
    
        @Override
    public boolean update(Contato entity) {
        boolean b = false;

        if (entity != null) {
            b = this.contatoDAO.update(entity);
        }

        return b;
    }

    @Override
    public Contato recuperaContato(long idContato) {
        Contato c = null;
        if (idContato > 0) {
            c = this.contatoDAO.recuperaContato(idContato);
        }
        return c;
    }

    @Override
    public Contato recuperaContatoPessoa(long idPessoa) {
        Contato c = null;
        if (idPessoa > 0) {
            c = this.contatoDAO.recuperaContatoPessoa(idPessoa);
        }
        return c;
    }

    @Override
    public long recuperaUltimoId() {
        long id;
        id = this.contatoDAO.recuperaUltimoId();
        return id;
    }
}
