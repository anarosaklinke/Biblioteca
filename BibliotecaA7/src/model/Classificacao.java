/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

public class Classificacao {

    private final long idClassificacao;
    private String nome;

    public Classificacao(long idClassificacao) {
        this.idClassificacao = idClassificacao;
    }

    public long getIdClassificacao() {
        return idClassificacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
