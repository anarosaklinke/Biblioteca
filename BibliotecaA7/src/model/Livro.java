package model;

public class Livro {

    private final long idLivro;
    private long idPessoa;
    private String isbn;
    private String semelhantes;
    private String autores;
    private String dataPublicacao;
    private String titulo;
    private int estoque;
    private long idClassificacao;

    public Livro(long idLivro) {
        this.idLivro = idLivro;
    }

    public long getIdLivro() {
        return idLivro;
    }

    public long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(long idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getSemelhantes() {
        return semelhantes;
    }

    public void setSemelhantes(String semelhantes) {
        this.semelhantes = semelhantes;
    }

    public String getAutores() {
        return autores;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }

    public String getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(String dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public long getIdClassificacao() {
        return idClassificacao;
    }

    public void setIdClassificacao(long idClassificacao) {
        this.idClassificacao = idClassificacao;
    }

}
