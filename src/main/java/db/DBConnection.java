/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author SENA
 */
public class DBConnection {
    // Ajusta estos valores a tu entorno
    private static final String URL = "jdbc:mysql://localhost:3306/colegio?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "Qwerty2006.";

    private DBConnection() {
        // Evita instanciación
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
