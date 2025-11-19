/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locacaoveiculos.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Ricardo Gomes
 */
public class ConnectionFactory {
    
    private static final String URL = "jdbc:postgresql://localhost:5432/locadora";
    private static final String USER = "postgres";
    private static final String PASS = "admin1234";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco: " + e.getMessage());
            throw new RuntimeException("Falha na conexao com o banco de dados", e);
        }
    }
}