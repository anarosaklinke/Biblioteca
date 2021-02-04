package service;

import java.util.List;
import model.Pessoa;

public interface PessoaService {

    boolean save(Pessoa pessoa);

    Pessoa recuperaPessoaId(long idPessoa);

    long recuperaUltimoId();

    List<Pessoa> recuperaPessoa();

    boolean verificaCPF(String cpf);

    long idCPF(String cpf);

    boolean update(Pessoa pessoa, long idOld);

    boolean exclui(long idPessoa);
}
