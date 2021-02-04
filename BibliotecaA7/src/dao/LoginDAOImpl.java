package dao;

import model.Login;
import utils.FabricaConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginDAOImpl implements LoginDAO {

    private String usuario;

    @Override
    public boolean save(Login login) {
        boolean b = false;
        Connection con = null;
        PreparedStatement pstm = null;

        con = FabricaConexao.getConexao();
        if (con != null) {
            try {

                con.setAutoCommit(false);

                pstm = con.prepareStatement(INSERT_LOGIN);
                pstm.setLong(1, login.getIdLogin());
                pstm.setString(2, login.getUsuario());
                pstm.setString(3, login.getSenha());
                pstm.setBoolean(4, login.getAdmin());
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
    public boolean update(Login login, long idOld) {
        boolean b = false;
        Connection con = null;
        PreparedStatement pstm = null;

        con = FabricaConexao.getConexao();
        if (con != null) {
            try {

                con.setAutoCommit(false);

                pstm = con.prepareStatement(UPDATE);
                pstm.setString(1, login.getUsuario());
                pstm.setString(2, login.getSenha());
                pstm.setLong(3, idOld);
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
    public int consultaLogin(String usuario, String senha) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet res = null;

        int resultado = 0;
        con = FabricaConexao.getConexao();
        if (con != null) {
            try {
                Statement stm = con.createStatement();

                pstm = con.prepareStatement(CONSULTA_LOGIN);
                pstm.setString(1, usuario);
                res = pstm.executeQuery();

                if (res != null && res.next()) {

                    if (res.getNString("senha").equals(senha)) {

                        if (res.getBoolean("admin")) {
                            resultado = 2;
                        } else {
                            resultado = 1;
                        }

                    }
                }
                con.close();

            } catch (SQLException ex) {
                System.out.println("Message: " + ex);
            }
        }
        return resultado;
    }

    @Override
    public String consultaLoginUsuario(long idPessoa) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet res = null;

        String resultado = "Nao Encontrado";
        con = FabricaConexao.getConexao();
        if (con != null) {
            try {
                Statement stm = con.createStatement();

                pstm = con.prepareStatement(LOGIN_USUARIO);
                pstm.setLong(1, idPessoa);

                res = pstm.executeQuery();

                if (res != null && res.next()) {
                    resultado = res.getString("usuario");
                } else {
                    resultado = "Nao Encontrado";
                }
                con.close();

            } catch (SQLException ex) {
                System.out.println("Message: " + ex);
            }
        }
        return resultado;
    }

    @Override
    public long consultaLongUsuario(long idPessoa) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet res = null;

        long resultado = -1;
        con = FabricaConexao.getConexao();
        if (con != null) {
            try {
                Statement stm = con.createStatement();

                pstm = con.prepareStatement(IDLOGIN_USUARIO);
                pstm.setLong(1, idPessoa);

                res = pstm.executeQuery();

                if (res != null && res.next()) {
                    resultado = res.getLong("idLogin");
                } else {
                    resultado = -1;
                }
                con.close();

            } catch (SQLException ex) {
                System.out.println("Message: " + ex);
            }
        }
        return resultado;
    }

    @Override
    public boolean verificaUsuario(String usuario) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet res = null;

        boolean resultado = false;
        con = FabricaConexao.getConexao();
        if (con != null) {
            try {
                Statement stm = con.createStatement();

                pstm = con.prepareStatement(CONSULTA_LOGIN);
                pstm.setString(1, usuario);
                res = pstm.executeQuery();

                if (res != null && res.next()) {
                    resultado = true;
                }
                con.close();

            } catch (SQLException ex) {
                System.out.println("Message: " + ex);
            }
        }
        return resultado;
    }

    @Override
    public boolean exclui(long idLogin) {
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
                pstm.setLong(1, idLogin);

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
