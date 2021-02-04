package service;

import model.Contato;

public interface ContatoService {

    boolean save(Contato contato);

    boolean update(Contato contato);

    Contato recuperaContato(long idContato);

    long recuperaUltimoId();

    Contato recuperaContatoPessoa(long idPessoa);

    boolean exclui(long idContato);
}
