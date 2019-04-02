package dao.daoInterfaces;

public interface FindableByName<T> {
    T findByName(String name);
}
