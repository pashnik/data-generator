package dao;

import java.util.List;

public interface DaoInterface<T> {

    T findById(int id);

    void save(T element);

    void update(T element);

    void delete(T element);

    List<T> findAll();
}
