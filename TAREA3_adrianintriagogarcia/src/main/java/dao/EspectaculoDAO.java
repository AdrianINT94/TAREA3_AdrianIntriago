package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.ConnectionManager;
import model.Espectaculo;

public class EspectaculoDAO {

    private static final String TABLE = "espectaculo";
    private CoordinadorDAO coordinadorDAO = new CoordinadorDAO();

    // ---------- MAP ----------
    private Espectaculo mapRow(ResultSet rs) {
        try {
            Espectaculo e = new Espectaculo();
            e.setId(rs.getInt("idespectaculo"));
            e.setNombre(rs.getString("nombre"));
            e.setFechaInicio(rs.getDate("fechaInicio").toLocalDate());
            e.setFechaFin(rs.getDate("fechaFin").toLocalDate());
     
            
            return e;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    // ---------- INSERT ----------
    public void insert(Espectaculo e) {
        String sql = "INSERT INTO espectaculo (nombre, fechaInicio, fechaFin, idCoordinador) VALUES (?,?,?,?)";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, e.getNombre());
            stmt.setDate(2, Date.valueOf(e.getFechaInicio()));
            stmt.setDate(3, Date.valueOf(e.getFechaFin()));
            stmt.setInt(4, e.getCoordinador().getId());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // ---------- UPDATE ----------
    public void update(Espectaculo e) {
        String sql = "UPDATE espectaculo SET nombre=?, fechaInicio=?, fechaFin=?, idCoordinador=? WHERE idespectaculo=?";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, e.getNombre());
            stmt.setDate(2, Date.valueOf(e.getFechaInicio()));
            stmt.setDate(3, Date.valueOf(e.getFechaFin()));
            stmt.setInt(4, e.getCoordinador().getId());
            stmt.setInt(5, e.getId());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // ---------- DELETE ----------
    public void delete(int id) {
        String sql = "DELETE FROM espectaculo WHERE idespectaculo=?";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // ---------- FIND BY ID ----------
    public Espectaculo findById(int id) {
        String sql = "SELECT * FROM espectaculo WHERE idespectaculo=?";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) return mapRow(rs);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // ---------- FIND ALL ----------
    public List<Espectaculo> findAll() {
        List<Espectaculo> lista = new ArrayList<>();
        String sql = "SELECT * FROM espectaculo";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Espectaculo e = mapRow(rs);
                if (e != null) lista.add(e);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }
}