package dao.daoInterfaces;

public interface FindableByType<T> {
    T findByType(String type);
}
