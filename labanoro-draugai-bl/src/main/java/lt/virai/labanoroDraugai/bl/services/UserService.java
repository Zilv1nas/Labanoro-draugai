package lt.virai.labanoroDraugai.bl.services;

import lt.virai.labanoroDraugai.domain.entities.User;

/**
 * Created by Žilvinas on 2016-03-11.
 */
public interface UserService {
    User get(Integer id);

    void save(User user);

    void update(User user);

    void remove(User user);
}
