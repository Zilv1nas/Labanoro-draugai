package lt.virai.labanoroDraugai.domain.dao;

import java.util.List;

/**
 * Created by Mantas on 4/19/2016.
 */
public interface DAO<T> {
    T get(Integer id);

    void save(T entity);

    void update(T entity);

    void remove(T entity);

    void remove(Integer id);

    List<T> getAll();
}
