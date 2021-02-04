package service;

import dao.DaoFactory;
import model.Pessoa;
import dao.PessoaDAO;
import java.util.List;

public class PessoaServiceImpl implements PessoaService {

    private PessoaDAO pessoaDAO;

    public PessoaServiceImpl() {
        this.pessoaDAO = DaoFactory.getClienteDAO();
    }

    @Override
    public boolean save(Pessoa entity) {
        boolean b = false;

        if (entity != null) {
            b = this.pessoaDAO.save(entity);
        }

        return b;
    }

    @Override
    public boolean exclui(long idPessoa) {
        boolean b = false;

        if (idPessoa > 0) {
            b = this.pessoaDAO.exclui(idPessoa);
        }

        return b;
    }

    @Override
    public boolean update(Pessoa pessoa, long idOld) {
        boolean b = false;

        if (pessoa != null) {
            if (idOld > 0) {
                b = this.pessoaDAO.update(pessoa, idOld);
            }
        }
        return b;
    }

    @Override
    public boolean verificaCPF(String cpf) {
        boolean b = false;
        if (cpf != null) {
            b = this.pessoaDAO.verificaCPF(cpf);
        }
        return b;
    }

    @Override
    public Pessoa recuperaPessoaId(long idpessoa) {
        Pessoa c = null;
        if (idpessoa > 0) {
            c = this.pessoaDAO.recuperaPessoaId(idpessoa);
        }
        return c;
    }

    @Override
    public long recuperaUltimoId() {
        long id;
        id = this.pessoaDAO.recuperaUltimoId();
        return id;
    }

    @Override
    public List<Pessoa> recuperaPessoa() {
        List<Pessoa> b;
        b = this.pessoaDAO.recuperaPessoa();
        return b;
    }

    @Override
    public long idCPF(String cpf) {
        long id = -1;
        if (cpf != null) {
            id = this.pessoaDAO.idCPF(cpf);
        }
        return id;
    }
}
