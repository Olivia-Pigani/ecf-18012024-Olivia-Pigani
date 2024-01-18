package dao;

import java.util.List;

public interface BaseDAO<T> {

    public boolean add(T element);

    public T getById(int id);

    public List<T> getAll();

    public boolean update(int id, T element);

    public boolean delete(int id);


}
