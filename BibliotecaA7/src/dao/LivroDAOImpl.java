package dao;

import model.Livro;
import utils.FabricaConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LivroDAOImpl implements LivroDAO {

    @Override
    public boolean save(Livro livro) {
        boolean b = false;
        Connection con = null;
        PreparedStatement pstm = null;

        con = FabricaConexao.getConexao();
        if (con != null) {
            try {

                con.setAutoCommit(false);

                pstm = con.prepareStatement(INSERT);
                pstm.setLong(1, livro.getIdLivro());
                pstm.setString(2, livro.getDataPublicacao());
                pstm.setString(3, livro.getIsbn());
                pstm.setString(4, livro.getAutores());
                pstm.setString(5, livro.getSemelhantes());
                pstm.setString(6, livro.getTitulo());
                pstm.setLong(7, livro.getIdPessoa());

                pstm.executeUpdate();

                con.commit();
                con.close();
                b = true;
            } catch (SQLException ex) {
                System.out.println("Message: " + ex);
            }
        }
        return b;
    }

    @Override
    public boolean verificaId(long id) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet res = null;
        boolean temp = false;
        con = FabricaConexao.getConexao();

        if (con != null) {
            try {

                Statement stm = con.createStatement();
                pstm = con.prepareStatement(VERIFICA_ID);
                pstm.setLong(1, id);
                res = pstm.executeQuery();

                if (res != null && res.next()) {
                    temp = true;
                } else {
                    temp = false;
                }
                con.close();

            } catch (SQLException ex) {
                System.out.println("Message: " + ex);
            }
        }

        return temp;
    }

    @Override
    public long recuperaUltimoId() {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet res = null;
        long temp = -1;
        con = FabricaConexao.getConexao();

        if (con != null) {
            try {

                Statement stm = con.createStatement();
                pstm = con.prepareStatement(RECUPERA_ID);
                res = pstm.executeQuery();

                if (res != null && res.next()) {
                    temp = res.getLong(1);
                } else {
                    temp = -1;
                }
                con.close();

            } catch (SQLException ex) {
                System.out.println("Message: " + ex);
            }
        }

        return temp;
    }

    @Override
    public List<Livro> recuperaLivro() {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet res = null;
        List<Livro> temp = new ArrayList<Livro>();
        con = FabricaConexao.getConexao();
        if (con != null) {
            try {

                pstm = con.prepareStatement(CONSULTA_LIVRO);
                res = pstm.executeQuery();

                Livro livro;
                while (res != null && res.next()) {
                    livro = new Livro(res.getLong("idLivro"));
                    livro.setAutores(res.getNString("autores"));
                    livro.setDataPublicacao(res.getNString("dataPublicacao"));
                    livro.setIdPessoa(res.getLong("Pessoa_idPessoa"));
                    livro.setIsbn(res.getNString("isbn"));
                    livro.setSemelhantes(res.getNString("semelhantes"));
                    livro.setTitulo(res.getNString("titulo"));
                    temp.add(livro);
                }
                con.close();
            } catch (SQLException ex) {
                System.out.println("Message: " + ex);
            }
        }

        return temp;

    }

    @Override
    public List<Livro> recuperaLivro(String chave) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet res = null;
        List<Livro> temp = new ArrayList<Livro>();
        con = FabricaConexao.getConexao();
        if (con != null) {
            try {
                chave = "%" + chave + "%";
                pstm = con.prepareStatement(CHAVE);
                pstm.setString(1, chave);
                pstm.setString(2, chave);
                pstm.setString(3, chave);
                pstm.setString(4, chave);
                pstm.setString(5, chave);

                res = pstm.executeQuery();

                Livro livro;
                while (res != null && res.next()) {
                    livro = new Livro(res.getLong("idLivro"));
                    livro.setAutores(res.getNString("autores"));
                    livro.setDataPublicacao(res.getNString("dataPublicacao"));
                    livro.setIdPessoa(res.getLong("Pessoa_idPessoa"));
                    livro.setIsbn(res.getNString("isbn"));
                    livro.setSemelhantes(res.getNString("semelhantes"));
                    livro.setTitulo(res.getNString("titulo"));
                    temp.add(livro);
                }
                con.close();
            } catch (SQLException ex) {
                System.out.println("Message: " + ex);
            }
        }

        return temp;

    }

    @Override
    public List<Livro> recuperaLivroPessoa(long idPessoa) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet res = null;
        List<Livro> temp = new ArrayList<Livro>();
        con = FabricaConexao.getConexao();
        if (con != null) {
            try {

                pstm = con.prepareStatement(LIVRO_PESSOA);
                pstm.setLong(1, idPessoa);
                res = pstm.executeQuery();

                Livro livro;
                while (res != null && res.next()) {
                    livro = new Livro(res.getLong("idLivro"));
                    livro.setAutores(res.getNString("autores"));
                    livro.setDataPublicacao(res.getNString("dataPublicacao"));
                    livro.setIdPessoa(res.getLong("Pessoa_idPessoa"));
                    livro.setIsbn(res.getNString("isbn"));
                    livro.setSemelhantes(res.getNString("semelhantes"));
                    livro.setTitulo(res.getNString("titulo"));
                    temp.add(livro);
                }
                con.close();
            } catch (SQLException ex) {
                System.out.println("Message: " + ex);
            }
        }

        return temp;

    }

    @Override
    public long recuperaIsbn(String isbn) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet res = null;
        long temp = -1;
        con = FabricaConexao.getConexao();

        if (con != null) {
            try {

                Statement stm = con.createStatement();
                pstm = con.prepareStatement(VERIFICA_ISBN);
                pstm.setString(1, isbn);
                res = pstm.executeQuery();

                if (res != null && res.next()) {
                    temp = res.getLong(1);
                } else {
                    temp = -1;
                }
                con.close();

            } catch (SQLException ex) {
                System.out.println("Message: " + ex);
            }
        }

        return temp;
    }

    public boolean update(Livro livro, long idPessoa) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet res = null;
        con = FabricaConexao.getConexao();
        boolean b = false;
        if (con != null) {
            try {

                if (livro.getSemelhantes().isEmpty()) {
                    pstm = con.prepareStatement(UPDATE);
                    pstm.setString(1, livro.getTitulo());
                    pstm.setString(2, livro.getDataPublicacao());
                    pstm.setString(3, livro.getAutores());
                    pstm.setLong(4, livro.getIdPessoa());
                    pstm.setLong(5, livro.getIdLivro());
                } else {
                    pstm = con.prepareStatement(UPDATE_S);
                    pstm.setString(1, livro.getTitulo());
                    pstm.setString(2, livro.getSemelhantes());
                    pstm.setString(3, livro.getDataPublicacao());
                    pstm.setString(4, livro.getAutores());
                    pstm.setLong(5, livro.getIdPessoa());
                    pstm.setLong(6, livro.getIdLivro());
                }

                pstm.execute();

                //con.commit();
                con.close();
                b = true;

            } catch (SQLException ex) {

                System.out.println("Message: " + ex);
            }
        }

        return b;

    }

    @Override
    public boolean updateIsbn(Livro livro) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet res = null;
        con = FabricaConexao.getConexao();
        boolean b = false;
        if (con != null) {
            try {

                if (livro.getSemelhantes().isEmpty()) {
                    pstm = con.prepareStatement(UPDATE_ISBN);
                    pstm.setString(1, livro.getTitulo());
                    pstm.setString(2, livro.getDataPublicacao());
                    pstm.setString(3, livro.getAutores());
                    pstm.setString(4, livro.getIsbn());
                } else {
                    pstm = con.prepareStatement(UPDATE_ISBN_S);
                    pstm.setString(1, livro.getTitulo());
                    pstm.setString(2, livro.getSemelhantes());
                    pstm.setString(3, livro.getDataPublicacao());
                    pstm.setString(4, livro.getAutores());
                    pstm.setString(5, livro.getIsbn());
                }

                pstm.execute();

                //con.commit();
                con.close();
                b = true;

            } catch (SQLException ex) {

                System.out.println("Message: " + ex);
            }
        }

        return b;

    }

    @Override
    public long verifica(String pessoa) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet res = null;
        long result = -1;
        con = FabricaConexao.getConexao();

        if (con != null) {
            try {

                Statement stm = con.createStatement();
                pstm = con.prepareStatement(VERIFICA);
                pstm.setString(1, pessoa);
                res = pstm.executeQuery();

                if (res != null && res.next()) {
                    result = res.getLong("idPessoa");
                } else {
                    result = -1;
                }
                con.close();

            } catch (SQLException ex) {
                System.out.println("Message: " + ex);
            }
        }

        return result;
    }

    @Override
    public boolean excluir(String isbn) {
        boolean b = false;
        Connection con = null;
        PreparedStatement pstm = null;

        con = FabricaConexao.getConexao();
        if (con != null) {
            try {

                con.setAutoCommit(false);

                pstm = con.prepareStatement(EXCLUIR);
                pstm.setString(1, isbn);

                pstm.executeUpdate();

                con.commit();
                con.close();
                b = true;
            } catch (SQLException ex) {
                System.out.println("Message: " + ex);
            }
        }
        return b;
    }

}
