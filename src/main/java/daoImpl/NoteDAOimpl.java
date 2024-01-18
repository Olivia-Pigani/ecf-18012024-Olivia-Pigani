package daoImpl;

import dao.BaseDAO;
import entities.Note;

import java.util.List;

public class NoteDAOimpl implements BaseDAO<Note> {
    @Override
    public boolean add(Note element) {
        return false;
    }

    @Override
    public Note getById(int id) {
        return null;
    }

    @Override
    public List<Note> getAll() {
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
