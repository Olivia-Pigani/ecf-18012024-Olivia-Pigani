package daoImpl;

import dao.BaseDAO;
import entities.Enseignant;

import java.util.List;

public class EnseignantDAOimpl implements BaseDAO<Enseignant> {
    @Override
    public boolean add(Enseignant element) {
        return false;
    }

    @Override
    public Enseignant getById(int id) {
        return null;
    }

    @Override
    public List<Enseignant> getAll() {
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
