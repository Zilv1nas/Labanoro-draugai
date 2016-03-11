package lt.virai.labanoroDraugai.bl.services.impl;

import lt.virai.labanoroDraugai.bl.services.UserService;
import lt.virai.labanoroDraugai.domain.dao.UserDAO;
import lt.virai.labanoroDraugai.domain.entities.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Objects;

/**
 * Created by Žilvinas on 2016-03-11.
 */
@Stateless
public class UserServiceImpl implements UserService {

    @Inject
    UserDAO userDAO;

    @Override
    public User get(Integer id) {
        return userDAO.get(Objects.requireNonNull(id));
    }

    @Override
    public void save(User user) {
        userDAO.save(Objects.requireNonNull(user));
    }

    @Override
    public void update(User user) {
        userDAO.update(Objects.requireNonNull(user));
    }

    @Override
    public void remove(User user) {
        userDAO.remove(Objects.requireNonNull(user));
    }
}
