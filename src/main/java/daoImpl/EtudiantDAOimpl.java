package daoImpl;

import dao.BaseDAO;
import entities.Etudiant;

import java.util.List;

public class EtudiantDAOimpl implements BaseDAO<Etudiant> {

    @Override
    public boolean add(Etudiant element) {
        return false;
    }

    @Override
    public Etudiant getById(int id) {
        return null;
    }

    @Override
    public List<Etudiant> getAll() {
        return null;
    }

    @Override
    public boolean update(int id) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
