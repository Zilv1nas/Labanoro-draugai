package lt.virai.labanoroDraugai.domain.dao;

import org.jinq.jpa.JinqJPAStreamProvider;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by Žilvinas on 2016-04-14.
 */
public abstract class AbstractDAO<T> implements DAO<T> {

    private Class<T> classType;

    @PersistenceContext(unitName = "labanoroDraugaiPersistenceUnit")
    protected EntityManager entityManager;

    protected JinqJPAStreamProvider streams;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.classType = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0]; //Type erasure workaround
    }

    @PostConstruct
    private void setStreams() {
        streams = new JinqJPAStreamProvider(entityManager.getMetamodel());
    }

    public T get(Integer id) {
        return entityManager.find(classType, Objects.requireNonNull(id));
    }

    public T save(T entity) {
        entityManager.persist(Objects.requireNonNull(entity));
        return entity;
    }

    public void update(T entity) {
        entityManager.merge(Objects.requireNonNull(entity));
    }

    public void remove(T entity) {
        entityManager.remove(Objects.requireNonNull(entity));
    }

    public void remove(Integer id) {
        Objects.requireNonNull(id);
        Optional.ofNullable(get(id)).ifPresent(this::remove);
    }

    public List<T> getAll() {
        return streams.streamAll(entityManager, classType).toList();
    }
}
