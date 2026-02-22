package edu.unir.app.dao;

import edu.unir.app.db.DBConnection;
import edu.unir.app.exceptions.DataAccessException;
import edu.unir.app.model.Estudiante;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAO {

    // CREATE
    public void insertar(Estudiante e) throws DataAccessException {
        String sql = """
            INSERT INTO estudiante (documento, nombres, apellidos, email, telefono, fecha_nacimiento, activo)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, e.getDocumento());
            ps.setString(2, e.getNombres());
            ps.setString(3, e.getApellidos());
            ps.setString(4, e.getEmail());
            ps.setString(5, e.getTelefono());

            if (e.getFechaNacimiento() != null) {
                ps.setDate(6, Date.valueOf(e.getFechaNacimiento()));
            } else {
                ps.setNull(6, Types.DATE);
            }

            ps.setBoolean(7, e.isActivo());

            ps.executeUpdate();

        } catch (SQLException ex) {
            // Ejemplo de "traducción" a excepción propia
            if (ex.getErrorCode() == 1062) { // duplicate key (documento unique)
                throw new DataAccessException("Ya existe un estudiante con ese documento.", ex);
            }
            throw new DataAccessException("Error insertando estudiante en BD.", ex);
        }
    }

    // READ (LIST)
    public List<Estudiante> listar() throws DataAccessException {
        String sql = """
            SELECT id, documento, nombres, apellidos, email, telefono, fecha_nacimiento, activo
            FROM estudiante
            ORDER BY id DESC
        """;

        List<Estudiante> estudiantes = new ArrayList<>();

        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                estudiantes.add(mapRow(rs));
            }

        } catch (SQLException ex) {
            throw new DataAccessException("Error listando estudiantes.", ex);
        }

        return estudiantes;
    }

    // READ (BY ID)
    public Estudiante buscarPorId(int id) throws DataAccessException {
        String sql = """
            SELECT id, documento, nombres, apellidos, email, telefono, fecha_nacimiento, activo
            FROM estudiante
            WHERE id = ?
        """;

        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
                return null;
            }

        } catch (SQLException ex) {
            throw new DataAccessException("Error buscando estudiante por id.", ex);
        }
    }

    // UPDATE
    public void actualizar(Estudiante e) throws DataAccessException {
        if (e.getId() == null) {
            throw new DataAccessException("No se puede actualizar: el id es null.");
        }

        String sql = """
            UPDATE estudiante
            SET documento = ?, nombres = ?, apellidos = ?, email = ?, telefono = ?, fecha_nacimiento = ?, activo = ?
            WHERE id = ?
        """;

        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, e.getDocumento());
            ps.setString(2, e.getNombres());
            ps.setString(3, e.getApellidos());
            ps.setString(4, e.getEmail());
            ps.setString(5, e.getTelefono());

            if (e.getFechaNacimiento() != null) {
                ps.setDate(6, Date.valueOf(e.getFechaNacimiento()));
            } else {
                ps.setNull(6, Types.DATE);
            }

            ps.setBoolean(7, e.isActivo());
            ps.setInt(8, e.getId());

            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new DataAccessException("No se actualizó: no existe estudiante con id=" + e.getId());
            }

        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1062) {
                throw new DataAccessException("No se pudo actualizar: documento duplicado.", ex);
            }
            throw new DataAccessException("Error actualizando estudiante.", ex);
        }
    }

    // DELETE (físico)
    public void eliminarPorId(int id) throws DataAccessException {
        String sql = "DELETE FROM estudiante WHERE id = ?";

        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);

            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new DataAccessException("No se eliminó: no existe estudiante con id=" + id);
            }

        } catch (SQLException ex) {
            throw new DataAccessException("Error eliminando estudiante.", ex);
        }
    }

    // Mapper (ResultSet -> Estudiante)
    private Estudiante mapRow(ResultSet rs) throws SQLException {
        Estudiante e = new Estudiante();
        e.setId(rs.getInt("id"));
        e.setDocumento(rs.getString("documento"));
        e.setNombres(rs.getString("nombres"));
        e.setApellidos(rs.getString("apellidos"));
        e.setEmail(rs.getString("email"));
        e.setTelefono(rs.getString("telefono"));

        Date fn = rs.getDate("fecha_nacimiento");
        e.setFechaNacimiento(fn != null ? fn.toLocalDate() : null);

        e.setActivo(rs.getBoolean("activo"));
        return e;
    }
}