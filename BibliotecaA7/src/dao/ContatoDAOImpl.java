package dao;

import model.Contato;
import utils.FabricaConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ContatoDAOImpl implements ContatoDAO {

    @Override
    public boolean save(Contato contato) {
        boolean b = false;
        Connection con = null;
        PreparedStatement pstm = null;

        con = FabricaConexao.getConexao();
        if (con != null) {
            try {

                con.setAutoCommit(false);

                pstm = con.prepareStatement(INSERT_CONTATO);
                pstm.setLong(1, contato.getIdContato());
                pstm.setString(2, contato.getTelRes());
                pstm.setString(3, contato.getTelCom());
                pstm.setString(4, contato.getEmail());
                pstm.setString(5, contato.getCelular());
                pstm.setLong(6, contato.getIdPessoa());
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
    public Contato recuperaContato(long idContato) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet res = null;
        Contato temp = new Contato(idContato);
        con = FabricaConexao.getConexao();

        if (con != null) {
            try {

                Statement stm = con.createStatement();

                pstm = con.prepareStatement(RECUPERA_CONTATO);
                pstm.setLong(1, idContato);
                res = pstm.executeQuery();

                if (res != null && res.next()) {
                    temp.setEmail(res.getNString("email"));
                    temp.setCelular(res.getNString("celular"));
                    temp.setTelRes(res.getNString("telefoneResidencial"));
                    temp.setTelCom(res.getNString("telefoneComercial"));
                } else {
                    temp = null;
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
    public Contato recuperaContatoPessoa(long idPessoa) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet res = null;
        Contato temp = new Contato(idPessoa);
        con = FabricaConexao.getConexao();

        if (con != null) {
            try {

                Statement stm = con.createStatement();
                pstm = con.prepareStatement(RECUPERA_CONTATO_PESSOA);
                pstm.setLong(1, idPessoa);
                res = pstm.executeQuery();

                if (res != null && res.next()) {
                    temp.setEmail(res.getNString("email"));
                    temp.setCelular(res.getNString("celular"));
                    temp.setTelRes(res.getNString("telefoneResidencial"));
                    temp.setTelCom(res.getNString("telefoneComercial"));
                } else {
                    temp = null;
                }
                con.close();

            } catch (SQLException ex) {
                System.out.println("Message: " + ex);
            }
        }

        return temp;
    }

    @Override
    public boolean update(Contato contato) {
        boolean b = false;
        Connection con = null;
        PreparedStatement pstm = null;

        con = FabricaConexao.getConexao();
        if (con != null) {
            try {

                con.setAutoCommit(false);

                pstm = con.prepareStatement(UPDATE);
                pstm.setString(1, contato.getTelRes());
                pstm.setString(2, contato.getTelCom());
                pstm.setString(3, contato.getEmail());
                pstm.setString(4, contato.getCelular());
                pstm.setLong(5, contato.getIdPessoa());

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
    public boolean exclui(long idContato) {
        boolean b = false;
        Connection con = null;
        PreparedStatement pstm = null;

        con = FabricaConexao.getConexao();
        if (con != null) {
            try {

                con.setAutoCommit(false);

                pstm = con.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
                pstm.execute("SET FOREIGN_KEY_CHECKS=0");

                pstm = con.prepareStatement(EXCLUIR);
                pstm.setLong(1, idContato);

                pstm.executeUpdate();

                pstm.execute("SET FOREIGN_KEY_CHECKS=1");

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
