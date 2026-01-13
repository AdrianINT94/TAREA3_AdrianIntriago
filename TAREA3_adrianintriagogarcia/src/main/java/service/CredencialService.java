package service;

import java.util.List;

import dao.CredencialDAO;
import model.Credencial;

public class CredencialService {

    private CredencialDAO dao = new CredencialDAO();

    // LOGIN (CU2)
    public Credencial getByUsername(String username) {
        return dao.findByUsername(username);
    }

    // ADMIN
    public List<Credencial> getAll() {
        return dao.findAll();
    }

    public void save(Credencial c) {
        if (dao.findByUsername(c.getUsername()) == null) {
            dao.insert(c);
        } else {
            dao.update(c);
        }
    }

    public void delete(String username) {
        dao.delete(username);
    }
}