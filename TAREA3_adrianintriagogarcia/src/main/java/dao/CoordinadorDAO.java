package dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import db.ConnectionManager;
import model.Coordinador;

public class CoordinadorDAO {

    public Coordinador findById(int idPersona) {
        String sql = """
            SELECT p.id, p.nombre, p.email, p.nacionalidad,
                   c.senior, c.fecha_Senior
            FROM persona p
            JOIN coordinacion c ON p.id = c.id_persona
            WHERE p.id = ?
        """;

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, idPersona);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Coordinador c = new Coordinador();
                c.setId(rs.getInt("id"));
                c.setNombre(rs.getString("nombre"));
                c.setEmail(rs.getString("email"));
                c.setNacionalidad(rs.getString("nacionalidad"));
                c.setSenior(rs.getBoolean("senior"));

                Date fecha = rs.getDate("fecha_Senior");
                if (fecha != null) {
                    c.setFechaSenior(fecha.toLocalDate());
                }

                return c;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Coordinador> findAll() {
        List<Coordinador> lista = new ArrayList<>();

        String sql = """
            SELECT p.id
            FROM persona p
            JOIN coordinacion c ON p.id = c.id_persona
        """;

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(findById(rs.getInt("id")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // Método para insertar un nuevo coordinador
    public void insert(Coordinador c) {
        String sqlPersona = "INSERT INTO persona(id, nombre, email, nacionalidad) VALUES (?, ?, ?, ?)";
        String sqlCoordinacion = "INSERT INTO coordinacion(id_persona, senior, fecha_Senior) VALUES (?, ?, ?)";

        try (Connection con = ConnectionManager.getConnection()) {
            con.setAutoCommit(false);

            try (PreparedStatement stmtPersona = con.prepareStatement(sqlPersona);
                 PreparedStatement stmtCoordinacion = con.prepareStatement(sqlCoordinacion)) {

                // Insertar en la tabla persona
                stmtPersona.setInt(1, c.getId());
                stmtPersona.setString(2, c.getNombre());
                stmtPersona.setString(3, c.getEmail());
                stmtPersona.setString(4, c.getNacionalidad());
                stmtPersona.executeUpdate();

                // Insertar en la tabla coordinacion
                stmtCoordinacion.setInt(1, c.getId());
                stmtCoordinacion.setBoolean(2, c.isSenior());
                stmtCoordinacion.setDate(3, c.getFechaSenior() != null ? Date.valueOf(c.getFechaSenior()) : null);
                stmtCoordinacion.executeUpdate();

                con.commit();
            } catch (SQLException e) {
                con.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para actualizar un coordinador
    public void update(Coordinador c) {
        String sqlPersona = "UPDATE persona SET nombre = ?, email = ?, nacionalidad = ? WHERE id = ?";
        String sqlCoordinacion = "UPDATE coordinacion SET senior = ?, fecha_Senior = ? WHERE id_persona = ?";

        try (Connection con = ConnectionManager.getConnection()) {
            con.setAutoCommit(false);

            try (PreparedStatement stmtPersona = con.prepareStatement(sqlPersona);
                 PreparedStatement stmtCoordinacion = con.prepareStatement(sqlCoordinacion)) {

                // Actualizar en la tabla persona
                stmtPersona.setString(1, c.getNombre());
                stmtPersona.setString(2, c.getEmail());
                stmtPersona.setString(3, c.getNacionalidad());
                stmtPersona.setInt(4, c.getId());
                stmtPersona.executeUpdate();

                // Actualizar en la tabla coordinacion
                stmtCoordinacion.setBoolean(1, c.isSenior());
                stmtCoordinacion.setDate(2, c.getFechaSenior() != null ? Date.valueOf(c.getFechaSenior()) : null);
                stmtCoordinacion.setInt(3, c.getId());
                stmtCoordinacion.executeUpdate();

                con.commit();
            } catch (SQLException e) {
                con.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar un coordinador
    public void delete(int id) {
        String sqlCoordinacion = "DELETE FROM coordinacion WHERE id_persona = ?";
        String sqlPersona = "DELETE FROM persona WHERE id = ?";

        try (Connection con = ConnectionManager.getConnection()) {
            con.setAutoCommit(false);

            try (PreparedStatement stmtCoordinacion = con.prepareStatement(sqlCoordinacion);
                 PreparedStatement stmtPersona = con.prepareStatement(sqlPersona)) {

                // Eliminar de la tabla coordinacion
                stmtCoordinacion.setInt(1, id);
                stmtCoordinacion.executeUpdate();

                // Eliminar de la tabla persona
                stmtPersona.setInt(1, id);
                stmtPersona.executeUpdate();

                con.commit();
            } catch (SQLException e) {
                con.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
