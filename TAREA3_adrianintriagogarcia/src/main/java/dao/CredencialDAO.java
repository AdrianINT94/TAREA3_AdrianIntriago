package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import db.ConnectionManager;
import model.Credencial;
import model.Rol;

public class CredencialDAO {

    private static final String TABLE = "credenciales";

    private Credencial mapRow(ResultSet rs) throws SQLException {
        return new Credencial(
            rs.getString("username"),
            rs.getString("password"),
            Rol.valueOf(rs.getString("perfil")),
            rs.getInt("id_persona")
        );
    }

    // ===== LOGIN =====
    public Credencial findByUsername(String username) {
        String sql = "SELECT * FROM " + TABLE + " WHERE username = ?";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            return rs.next() ? mapRow(rs) : null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // ===== ADMIN =====
    public List<Credencial> findAll() {
        List<Credencial> lista = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE;

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

    public void insert(Credencial c) {
        String sql = "INSERT INTO " + TABLE + " (username, password, perfil,id_persona) VALUES (?,?,?,?)";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, c.getUsername());
            stmt.setString(2, c.getPassword());
            stmt.setString(3, c.getRol().name());
            stmt.setInt(4, c.getIdPersona());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Credencial c) {
        String sql = "UPDATE " + TABLE + " SET password=?, perfil=? WHERE username=?";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, c.getPassword());
            stmt.setString(2, c.getRol().name());
            stmt.setString(3, c.getUsername());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String username) {
        String sql = "DELETE FROM " + TABLE + " WHERE username=?";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}