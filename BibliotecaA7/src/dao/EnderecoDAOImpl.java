package dao;

import static dao.ContatoDAO.RECUPERA_CONTATO_PESSOA;
import model.Endereco;
import utils.FabricaConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EnderecoDAOImpl implements EnderecoDAO {

    @Override
    public boolean save(Endereco endereco) {
        boolean b = false;
        Connection con = null;
        PreparedStatement pstm = null;

        con = FabricaConexao.getConexao();
        if (con != null) {
            try {

                con.setAutoCommit(false);

                pstm = con.prepareStatement(INSERT_ENDERECO);
                pstm.setLong(1, endereco.getIdEndereco());
                pstm.setString(2, endereco.getRua());
                pstm.setInt(3, endereco.getNumero());
                pstm.setString(4, endereco.getBairro());
                pstm.setString(5, endereco.getCidade());
                pstm.setString(6, endereco.getEstado());
                pstm.setString(7, endereco.getPais());
                pstm.setString(8, endereco.getCep());
                pstm.setLong(9, endereco.getIdPessoa());
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
                /**
                 *
                 */
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
    public Endereco recuperaEnderecoPessoa(long idPessoa) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet res = null;
        Endereco temp = new Endereco(idPessoa);
        con = FabricaConexao.getConexao();

        if (con != null) {
            try {

                Statement stm = con.createStatement();
                pstm = con.prepareStatement(RECUPERA_ENDERECO_PESSOA);
                pstm.setLong(1, idPessoa);
                res = pstm.executeQuery();

                if (res != null && res.next()) {
                    temp.setBairro(res.getNString("bairro"));
                    temp.setCep(res.getNString("cep"));
                    temp.setCidade(res.getNString("cidade"));
                    temp.setEstado(res.getNString("estado"));
                    temp.setNumero(res.getInt("numero"));
                    temp.setPais(res.getNString("pais"));
                    temp.setRua(res.getNString("rua"));
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
    public boolean update(Endereco endereco) {
        boolean b = false;
        Connection con = null;
        PreparedStatement pstm = null;

        con = FabricaConexao.getConexao();
        if (con != null) {
            try {
                /*
                 * Setando a conexão para falso, que representa o start da transação
                 */
                con.setAutoCommit(false);

                /*
                 * O trecho abaixo permite a inserção de uma classe Endereco na tabela Endereco
                 */
                pstm = con.prepareStatement(UPDATE);
                pstm.setString(1, endereco.getRua());
                pstm.setInt(2, endereco.getNumero());
                pstm.setString(3, endereco.getBairro());
                pstm.setString(4, endereco.getCidade());
                pstm.setString(5, endereco.getEstado());
                pstm.setString(6, endereco.getPais());
                pstm.setString(7, endereco.getCep());
                pstm.setLong(8, endereco.getIdPessoa());
                pstm.executeUpdate();
                /*
                 * Executando o commit da transação.  
                 */
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
    public boolean exclui(long idEndereco) {
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
                pstm.setLong(1, idEndereco);

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
