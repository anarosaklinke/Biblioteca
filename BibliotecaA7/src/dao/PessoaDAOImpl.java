package dao;

import static dao.LivroDAO.CONSULTA_LIVRO;
import model.Pessoa;
import utils.FabricaConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAOImpl implements PessoaDAO {

    @Override
    public boolean save(Pessoa pessoa) {
        boolean b = false;
        Connection con = null;
        PreparedStatement pstm = null;

        con = FabricaConexao.getConexao();
        if (con != null) {
            try {
                con.setAutoCommit(false);

                pstm = con.prepareStatement(INSERT_PESSOA);
                pstm.setLong(1, pessoa.getIdPessoa());
                pstm.setString(2, pessoa.getNome());
                pstm.setInt(3, pessoa.getIdade());
                pstm.setDate(4, pessoa.getDataNascimento());
                pstm.setString(5, pessoa.getCpf());
                pstm.setString(6, pessoa.getSexo());
                pstm.setLong(7, pessoa.geIdLogin());
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
    public boolean update(Pessoa pessoa, long idOld) {
        boolean b = false;
        Connection con = null;
        PreparedStatement pstm = null;

        con = FabricaConexao.getConexao();
        if (con != null) {
            try {
                con.setAutoCommit(false);

                pstm = con.prepareStatement(UPDATE);
                pstm.setString(1, pessoa.getNome());
                pstm.setInt(2, pessoa.getIdade());
                pstm.setDate(3, pessoa.getDataNascimento());
                pstm.setString(4, pessoa.getCpf());
                pstm.setString(5, pessoa.getSexo());
                pstm.setLong(6, idOld);
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
    public Pessoa recuperaPessoaId(long idpessoa) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet res = null;
        Pessoa temp = new Pessoa(idpessoa);
        con = FabricaConexao.getConexao();

        if (con != null) {
            try {

                Statement stm = con.createStatement();
                pstm = con.prepareStatement(RECUPERA_PESSOA);
                pstm.setLong(1, idpessoa);
                res = pstm.executeQuery();

                if (res != null && res.next()) {
                    temp.setNome(res.getNString("nome"));
                    temp.setIdade(res.getInt("idade"));
                    temp.setSexo(res.getNString("sexo"));
                    temp.setDataNascimento(res.getDate("dataNascimento"));
                    temp.setCpf(res.getNString("cpf"));
                    temp.setLogin(res.getLong("Login_idLogin"));
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
    public List<Pessoa> recuperaPessoa() {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet res = null;
        List<Pessoa> temp = new ArrayList<Pessoa>();
        con = FabricaConexao.getConexao();
        if (con != null) {
            try {

                pstm = con.prepareStatement(RECUPERA);
                res = pstm.executeQuery();

                Pessoa pessoa;
                while (res != null && res.next()) {
                    pessoa = new Pessoa(res.getLong("idPessoa"));
                    pessoa.setNome(res.getNString("nome"));
                    pessoa.setSexo(res.getNString("sexo"));
                    pessoa.setLogin(res.getLong("Login_idLogin"));
                    pessoa.setCpf(res.getNString("cpf"));
                    pessoa.setDataNascimento(res.getDate("dataNascimento"));
                    pessoa.setIdade(res.getInt("idade"));
                    temp.add(pessoa);
                }
                con.close();
            } catch (SQLException ex) {
                System.out.println("Message: " + ex);
            }
        }

        return temp;

    }

    @Override
    public boolean verificaCPF(String cpf) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet res = null;
        boolean temp = false;
        con = FabricaConexao.getConexao();

        if (con != null) {
            try {

                Statement stm = con.createStatement();
                pstm = con.prepareStatement(VERIFICA_CPF);
                pstm.setString(1, cpf);
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
    public long idCPF(String cpf) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet res = null;
        long temp = -1;
        con = FabricaConexao.getConexao();

        if (con != null) {
            try {

                Statement stm = con.createStatement();
                pstm = con.prepareStatement(VERIFICA_CPF);
                pstm.setString(1, cpf);
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
    public boolean exclui(long idPessoa) {
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
                pstm.setLong(1, idPessoa);

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
