package lt.virai.labanoroDraugai.domain.dao.impl;

import lt.virai.labanoroDraugai.domain.dao.UserDAO;
import lt.virai.labanoroDraugai.domain.entities.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Žilvinas on 2016-03-11.
 */

//TODO Remove if not needed
@Stateless
public class UserDAOImpl implements UserDAO {

    @PersistenceContext(unitName = "labanoroDraugaiPersistenceUnit")
    private EntityManager entityManager;

    @Override
    public User get(Integer id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void save(User thread) {
        entityManager.persist(thread);
    }

    @Override
    public void update(User thread) {
        entityManager.merge(thread);
    }

    @Override
    public void remove(User thread) {
        entityManager.remove(thread);
    }
}
