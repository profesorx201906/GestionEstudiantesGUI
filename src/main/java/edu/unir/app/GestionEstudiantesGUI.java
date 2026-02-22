/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package edu.unir.app;

import edu.unir.app.dao.EstudianteDAO;
import edu.unir.app.db.DBConnection;
import edu.unir.app.model.Estudiante;
import java.sql.Connection;
import java.time.LocalDate;

/**
 *
 * @author SENA
 */
public class GestionEstudiantesGUI {

    public static void main(String[] args) {
         EstudianteDAO dao = new EstudianteDAO();

        try {
            Estudiante nuevo = new Estudiante(null, "2001", "Javier", "Díaz",
                    "javier@mail.com", "3009998877", LocalDate.of(2002, 3, 15), true);

            dao.insertar(nuevo);

            dao.listar().forEach(System.out::println);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
