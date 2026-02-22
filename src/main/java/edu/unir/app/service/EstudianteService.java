/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.unir.app.service;


import edu.unir.app.dao.EstudianteDAO;
import edu.unir.app.exceptions.DataAccessException;
import edu.unir.app.exceptions.ValidationException;
import edu.unir.app.model.Estudiante;

import java.util.List;
import java.util.regex.Pattern;

public class EstudianteService {

    private final EstudianteDAO dao = new EstudianteDAO();

    // Validaciones simples (puedes endurecerlas en clase)
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");

    public void crear(Estudiante e) throws ValidationException, DataAccessException {
        validar(e);
        dao.insertar(e);
    }

    public void actualizar(Estudiante e) throws ValidationException, DataAccessException {
        if (e.getId() == null) {
            throw new ValidationException("Para actualizar, debe existir un ID seleccionado.");
        }
        validar(e);
        dao.actualizar(e);
    }

    public List<Estudiante> listar() throws DataAccessException {
        return dao.listar();
    }

    public Estudiante buscarPorDocumento(String documento) throws ValidationException, DataAccessException {
        if (documento == null || documento.trim().isEmpty()) {
            throw new ValidationException("Debe ingresar un documento.");
        }
        return dao.buscarPorDocumento(documento.trim());
    }

    // Eliminación lógica
    public void desactivar(int id) throws DataAccessException {
        dao.desactivarPorId(id);
    }

    private void validar(Estudiante e) throws ValidationException {
        if (e.getDocumento() == null || e.getDocumento().trim().isEmpty()) {
            throw new ValidationException("Documento es obligatorio.");
        }
        if (e.getNombres() == null || e.getNombres().trim().isEmpty()) {
            throw new ValidationException("Nombres es obligatorio.");
        }
        if (e.getApellidos() == null || e.getApellidos().trim().isEmpty()) {
            throw new ValidationException("Apellidos es obligatorio.");
        }

        String email = e.getEmail();
        if (email != null && !email.trim().isEmpty() && !EMAIL_PATTERN.matcher(email.trim()).matches()) {
            throw new ValidationException("Email no tiene un formato válido.");
        }
    }
}