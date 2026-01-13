package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import db.ConnectionManager;
import model.Numero;

public class NumeroDAO {

    // ---------- MAP ----------
    private Numero mapRow(ResultSet rs) throws SQLException {
        return new Numero(
            rs.getInt("idNumero"),
            rs.getString("nombre"),
            rs.getDouble("duracion"),
            rs.getInt("orden"),
            new ArrayList<>()
        );
    }

    // ---------- FIND BY ID ----------
    public Numero findById(int id) {
        String sql = "SELECT * FROM numero WHERE idNumero = ?";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            return rs.next() ? mapRow(rs) : null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // ---------- FIND ALL ----------
    public List<Numero> findAll() {
        List<Numero> lista = new ArrayList<>();
        String sql = "SELECT * FROM numero";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapRow(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // ---------- INSERT ----------
    public void insert(Numero n) {
        String sql = "INSERT INTO numero (nombre, duracion, orden) VALUES (?, ?, ?)";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, n.getNombre());
            stmt.setDouble(2, n.getDuracion());
            stmt.setInt(3, n.getOrden());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ---------- UPDATE ----------
    public void update(Numero n) {
        String sql = "UPDATE numero SET nombre = ?, duracion = ?, orden = ? WHERE idNumero = ?";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, n.getNombre());
            stmt.setDouble(2, n.getDuracion());
            stmt.setInt(3, n.getOrden());
            stmt.setInt(4, n.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ---------- DELETE ----------
    public void delete(int id) {
        String sql = "DELETE FROM numero WHERE idNumero = ?";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}