package lt.virai.labanoroDraugai.domain.dao;

/**
 * Created by Mantas on 4/19/2016.
 */
interface DAO<T> {
     T get(Integer id);
     void save(T entity);
     void update(T entity);
     void remove(T entity);
}
