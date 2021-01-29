package dao;

import utils.FabricaConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Classificacao;

public class ClassificacaoDAOImpl implements ClassificacaoDAO {

    @Override
    public boolean save(Classificacao classificacao) {
        boolean b = false;
        Connection con = null;
        PreparedStatement pstm = null;

        con = FabricaConexao.getConexao();
        if (con != null) {
            try {

                con.setAutoCommit(false);

                pstm = con.prepareStatement(INSERT);
                pstm.setLong(1, classificacao.getIdClassificacao());
                pstm.setString(2, classificacao.getNome());

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
    public boolean excluir(String nome) {
        boolean b = false;
        Connection con = null;
        PreparedStatement pstm = null;

        con = FabricaConexao.getConexao();
        if (con != null) {
            try {

                con.setAutoCommit(false);

                pstm = con.prepareStatement("SET SQL_SAFE_UPDATES = 0");
                pstm.execute("SET SQL_SAFE_UPDATES = 0");
                
                pstm = con.prepareStatement(EXCLUIR);
                pstm.setString(1, nome);

                
                pstm.executeUpdate();
                
                pstm.execute("SET SQL_SAFE_UPDATES = 1");

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
    public boolean update(String nome, long idOld) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet res = null;
        con = FabricaConexao.getConexao();
        boolean b = false;
        if (con != null) {
            try {

                pstm = con.prepareStatement(UPDATE);
                pstm.setString(1, nome);
                pstm.setLong(1, idOld);
                pstm.execute();

                con.close();
                b = true;

            } catch (SQLException ex) {

                System.out.println("Message: " + ex);
            }
        }

        return b;

    }

    @Override
    public long verifica(String nome) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet res = null;
        long result = -1;
        con = FabricaConexao.getConexao();

        if (con != null) {
            try {

                Statement stm = con.createStatement();
                pstm = con.prepareStatement(VERIFICA_NOME);
                pstm.setString(1, nome);
                res = pstm.executeQuery();

                if (res != null && res.next()) {
                    result = res.getLong("idClassificacao");
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
    public String verificaNome(long id) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet res = null;
        String result = null;
        con = FabricaConexao.getConexao();

        if (con != null) {
            try {

                Statement stm = con.createStatement();
                pstm = con.prepareStatement(VERIFICA_NOME_ID);
                pstm.setLong(1, id);
                
                res = pstm.executeQuery();

                while (res != null && res.next()) {
                    result = res.getNString("nome");
                } 
                con.close();

            } catch (SQLException ex) {
                System.out.println("Message: " + ex);
            }
        }

        return result;
    }
   
        @Override
    public List<Classificacao> recuperaClassificacao() {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet res = null;
        List<Classificacao> temp = new ArrayList<Classificacao>();
        con = FabricaConexao.getConexao();
        if (con != null) {
            try {

                pstm = con.prepareStatement(RECUPERA_CLASSIFICACAO);
                res = pstm.executeQuery();

                Classificacao livro;
                while (res != null && res.next()) {
                    livro = new Classificacao(res.getLong("idClassificacao"));
                    livro.setNome(res.getNString("nome"));
                    temp.add(livro);
                }
                con.close();
            } catch (SQLException ex) {
                System.out.println("Message: " + ex);
            }
        }

        return temp;

    }

}
