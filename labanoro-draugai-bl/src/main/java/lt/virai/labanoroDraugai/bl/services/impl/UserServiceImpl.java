package lt.virai.labanoroDraugai.bl.services.impl;

import lt.virai.labanoroDraugai.bl.services.UserService;
import lt.virai.labanoroDraugai.domain.dao.UserDAO;
import lt.virai.labanoroDraugai.domain.entities.User;
import lt.virai.labanoroDraugai.domain.model.UserRole;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
    public User save(User user) {
        return userDAO.save(Objects.requireNonNull(user));
    }

    @Override
    public void update(User user) {
        userDAO.update(Objects.requireNonNull(user));
    }

    @Override
    public void remove(Integer userId) {
        userDAO.remove(Objects.requireNonNull(userId));
    }

    @Override
    public List<User> getAll() {
        return userDAO.getAll();
    }

    @Override
    public void verifyUser(int userId) {
        Optional.ofNullable(userDAO.get(userId)).ifPresent(u -> {
            if (UserRole.CANDIDATE.equals(u.getRole())) {
                u.setRole(UserRole.MEMBER);
            }
        });
    }

    @Override
    public void updateUserProfile(User user) {
        Objects.requireNonNull(user);

        Optional<User> persistenUser = Optional.ofNullable(userDAO.get(user.getId()));
        persistenUser.ifPresent(u -> {
            u.setEmail(user.getEmail());
            u.setSurname(user.getSurname());
            u.setName(user.getName());
        });
    }
}
