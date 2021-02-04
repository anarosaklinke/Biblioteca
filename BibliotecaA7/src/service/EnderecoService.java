package service;

import model.Endereco;

public interface EnderecoService {

    boolean save(Endereco endereco);

    boolean update(Endereco endereco);

    long recuperaUltimoId();

    Endereco recuperaEnderecoPessoa(long idPessoa);

    boolean exclui(long idEndereco);
}
