package daoImpl;

import dao.BaseDAO;
import entities.EmploiDuTemps;

import java.util.List;

public class EmploiDTDAOimpl implements BaseDAO<EmploiDuTemps> {

    @Override
    public boolean add(EmploiDuTemps element) {
        return false;
    }

    @Override
    public EmploiDuTemps getById(int id) {
        return null;
    }

    @Override
    public List<EmploiDuTemps> getAll() {
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
