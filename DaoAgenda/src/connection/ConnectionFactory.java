/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author jose
 */
public class ConnectionFactory {

    private static String DRIVER = "com.mysql.jdbc.Driver";
    private static String URL = "jdbc:mysql://localhost:3306/db_Agenda";
    private static String USER = "root";
    private static String PASSWORD = "";

    public static Connection getConnection() {

        try {
            Class.forName(DRIVER);

            return DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro ao estabelecer a comunicação com o banco de dados", ex);
        }

    }

    public static void closeConnection(Connection conexao) {
        if (conexao != null) {
            try {
                conexao.close();
            } catch (SQLException ex) {
                System.err.println("Erro ao fechar conexão:" + ex);
            }

        }

    }

    public static void closeConnection(Connection conexao, PreparedStatement stmt) {

        if (conexao != null) {

            try {
                conexao.close();
                stmt.close();
            } catch (SQLException ex) {
                System.err.println("Erro ao fechar conexão:" + ex);

            }

        }

    }

    public static void closeConnection(Connection conexao, PreparedStatement stmt, ResultSet rs) {

        if (conexao != null) {
            try {
                conexao.close();
                stmt.close();
                rs.close();
            } catch (SQLException ex) {
                System.err.println("Erro ao fechar conexão:" + ex);
            }

        }

    }

}
