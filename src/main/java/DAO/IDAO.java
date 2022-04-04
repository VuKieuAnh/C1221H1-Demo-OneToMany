package DAO;

import java.util.List;

public interface IDAO<T> {
    List<T> findAll();
    T findById(int id);
    boolean update(T t);
    boolean save(T t);

}
