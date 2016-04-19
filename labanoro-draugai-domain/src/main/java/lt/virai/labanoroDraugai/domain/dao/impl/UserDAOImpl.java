package lt.virai.labanoroDraugai.domain.dao.impl;

import lt.virai.labanoroDraugai.domain.dao.AbstractDAO;
import lt.virai.labanoroDraugai.domain.dao.UserDAO;
import lt.virai.labanoroDraugai.domain.entities.User;

import javax.ejb.Stateless;
import java.util.Objects;

/**
 * Created by Žilvinas on 2016-03-11.
 */

@Stateless
public class UserDAOImpl extends AbstractDAO<User> implements UserDAO {

    @Override
    public User getByUsername(String username) {
        Objects.requireNonNull(username, "Username is required");

        return streams.streamAll(entityManager, User.class)
                .where(u -> u.getEmail().equals(username))
                .findOne()
                .orElse(null);
    }
}
