package model;

import java.sql.Date;

public class Pessoa {

    private final long idPessoa;
    private String sexo;
    private long idLogin;
    private String nome;
    private Date dataNascimento;
    private String cpf;
    private int idade;

    public Pessoa(long idPessoa) {
        this.idPessoa = idPessoa;
    }

    public long getIdPessoa() {
        return idPessoa;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public long geIdLogin() {
        return idLogin;
    }

    public void setLogin(long idLogin) {
        this.idLogin = idLogin;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
