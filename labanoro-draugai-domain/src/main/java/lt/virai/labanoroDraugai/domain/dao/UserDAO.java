package lt.virai.labanoroDraugai.domain.dao;

import lt.virai.labanoroDraugai.domain.entities.User;
import lt.virai.labanoroDraugai.domain.model.AuthAttributeEnum;

/**
 * Created by Žilvinas on 2016-03-11.
 */
public interface UserDAO extends DAO<User> {

    User getByUsername(String username);

    User getUserByAuthAttribute(AuthAttributeEnum name, String value);
}
