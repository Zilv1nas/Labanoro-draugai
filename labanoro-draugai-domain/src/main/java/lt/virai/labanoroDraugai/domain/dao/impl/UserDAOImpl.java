package lt.virai.labanoroDraugai.domain.dao.impl;

import lt.virai.labanoroDraugai.domain.dao.UserDAO;
import lt.virai.labanoroDraugai.domain.entities.AuthenticationAttribute;
import lt.virai.labanoroDraugai.domain.entities.AuthenticationAttribute_;
import lt.virai.labanoroDraugai.domain.entities.User;
import lt.virai.labanoroDraugai.domain.entities.User_;
import lt.virai.labanoroDraugai.domain.model.AuthAttributeEnum;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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

    @Override
    public User getUserFromLogin(String username, String password) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        Join<User, AuthenticationAttribute> authAttrJoin = root.join(User_.authenticationAttributes);

        Predicate usernamePredicate = builder.equal(root.get(User_.email), username);
        Predicate attrTypePredicate = builder.equal(authAttrJoin.get(AuthenticationAttribute_.name), AuthAttributeEnum.PASSWORD);
        Predicate attrValuePredicate = builder.equal(authAttrJoin.get(AuthenticationAttribute_.value), password);
        criteriaQuery.where(builder.and(usernamePredicate, attrTypePredicate, attrValuePredicate));

        try {
            return entityManager.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }
}
