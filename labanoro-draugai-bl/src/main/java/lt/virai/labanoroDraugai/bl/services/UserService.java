package lt.virai.labanoroDraugai.bl.services;

import lt.virai.labanoroDraugai.domain.entities.User;

import java.util.List;

/**
 * Created by Žilvinas on 2016-03-11.
 */
public interface UserService {
    User get(Integer id);

    User save(User user);

    void update(User user);

    void remove(Integer userId);

    List<User> getAll();

    void verifyUser(int userId);

    void updateUserProfile(User user);
}
