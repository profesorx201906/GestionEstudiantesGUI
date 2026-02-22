/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package edu.unir.app;

import db.DBConnection;
import java.sql.Connection;

/**
 *
 * @author SENA
 */
public class GestionEstudiantesGUI {

    public static void main(String[] args) {
        try (Connection cn = DBConnection.getConnection()) {
            System.out.println("✅ Conexión exitosa: " + (cn != null));
        } catch (Exception e) {
            System.out.println("❌ Error de conexión: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
