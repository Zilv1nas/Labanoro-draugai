package lt.virai.labanoroDraugai.domain.dao;

import lt.virai.labanoroDraugai.domain.entities.User;

/**
 * Created by Žilvinas on 2016-03-11.
 */
public interface UserDAO {
    User get(Integer id);

    void save(User user);

    void update(User user);

    void remove(User user);

    User getUserFromLogin(String username, String password);
}
