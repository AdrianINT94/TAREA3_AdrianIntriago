package dao;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

import db.ConnectionManager;
import model.Artista;
import model.Especialidad;

public class ArtistaDAO {

    private static final String TABLE = "artista";

    private String toEspecialidadesString(List<Especialidad> especialidades) {
        if (especialidades == null || especialidades.isEmpty()) return "";
        return especialidades.stream()
                .map(Especialidad::name)
                .collect(Collectors.joining(","));
    }

    private List<Especialidad> toEspecialidadesList(String s) {
        List<Especialidad> lista = new ArrayList<>();
        if (s == null || s.isEmpty()) return lista;
        for (String e : s.split(",")) {
            lista.add(Especialidad.valueOf(e));
        }
        return lista;
    }

    public void insert(Artista a) {
        String sql = "INSERT INTO " + TABLE +
                " (nombre, email, nacionalidad, apodo, especialidades) VALUES (?,?,?,?,?)";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, a.getNombre());
            stmt.setString(2, a.getEmail());
            stmt.setString(3, a.getNacionalidad());
            stmt.setString(4, a.getApodo());
            stmt.setString(5, toEspecialidadesString(a.getEspecialidades()));
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Artista a) {
        String sql = "UPDATE " + TABLE +
                " SET nombre=?, email=?, nacionalidad=?, apodo=?, especialidades=? WHERE id=?";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, a.getNombre());
            stmt.setString(2, a.getEmail());
            stmt.setString(3, a.getNacionalidad());
            stmt.setString(4, a.getApodo());
            stmt.setString(5, toEspecialidadesString(a.getEspecialidades()));
            stmt.setInt(6, a.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM " + TABLE + " WHERE id=?";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Artista findById(int id) {
        String sql = "SELECT * FROM " + TABLE + " WHERE id=?";

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

    public List<Artista> findAll() {
        List<Artista> lista = new ArrayList<>();
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

    private Artista mapRow(ResultSet rs) throws SQLException {
        Artista a = new Artista();
        a.setId(rs.getInt("id"));
        a.setNombre(rs.getString("nombre"));
        a.setEmail(rs.getString("email"));
        a.setNacionalidad(rs.getString("nacionalidad"));
        a.setApodo(rs.getString("apodo"));
        a.setEspecialidades(toEspecialidadesList(rs.getString("especialidades")));
        return a;
    }
}
