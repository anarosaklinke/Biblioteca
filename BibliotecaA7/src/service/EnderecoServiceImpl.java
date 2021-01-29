package service;

import dao.DaoFactory;
import dao.EnderecoDAO;
import model.Endereco;

public class EnderecoServiceImpl implements EnderecoService {

    private EnderecoDAO enderecoDAO;

    public EnderecoServiceImpl() {
        this.enderecoDAO = DaoFactory.getEnderecoDAO();
    }

    @Override
    public boolean save(Endereco entity) {
        boolean b = false;

        if (entity != null) {
            b = this.enderecoDAO.save(entity);
        }

        return b;
    }
    
            @Override
    public boolean exclui(long idEndereco) {
        boolean b = false;

        if (idEndereco > 0) {
            b = this.enderecoDAO.exclui(idEndereco);
        }

        return b;
    }

    @Override
    public boolean update(Endereco entity) {
        boolean b = false;

        if (entity != null) {
            b = this.enderecoDAO.update(entity);
        }

        return b;
    }

    @Override
    public long recuperaUltimoId() {
        long id;
        id = this.enderecoDAO.recuperaUltimoId();
        return id;
    }

    @Override
    public Endereco recuperaEnderecoPessoa(long idPessoa) {
        Endereco c = null;
        if (idPessoa > 0) {
            c = this.enderecoDAO.recuperaEnderecoPessoa(idPessoa);
        }
        return c;
    }
}
