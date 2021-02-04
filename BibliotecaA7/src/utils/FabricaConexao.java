package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FabricaConexao implements IMySQL {

    public static Connection getConexao() {
        Connection con = null;

        try {
            Class.forName(DRIVER_NAME);

            con = DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Message: " + ex.getMessage());
        }

        return con;
    }

    public boolean fechar(Connection con) {
        return false;
    }

    public boolean fechar(Connection con, PreparedStatement pstm) {
        return false;
    }

    public boolean fechar(Connection con, PreparedStatement pstm, ResultSet res) {
        return false;
    }
}
