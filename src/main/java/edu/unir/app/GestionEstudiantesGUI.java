/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package edu.unir.app;

import edu.unir.app.dao.EstudianteDAO;
import edu.unir.app.db.DBConnection;
import edu.unir.app.model.Estudiante;
import edu.unir.app.ui.EstudianteForm;
import java.sql.Connection;
import java.time.LocalDate;

/**
 *
 * @author SENA
 */
public class GestionEstudiantesGUI {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new EstudianteForm().setVisible(true));
    }
}
