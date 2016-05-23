package lt.virai.labanoroDraugai.domain.dao.impl;

import lt.virai.labanoroDraugai.domain.dao.AbstractDAO;
import lt.virai.labanoroDraugai.domain.dao.UserDAO;
import lt.virai.labanoroDraugai.domain.entities.AuthenticationAttribute;
import lt.virai.labanoroDraugai.domain.entities.User;
import lt.virai.labanoroDraugai.domain.model.AuthAttributeEnum;
import lt.virai.labanoroDraugai.domain.model.UserRole;
import org.jinq.orm.stream.JinqStream;
import org.jinq.tuples.Pair;

import javax.ejb.Stateless;
import java.util.List;
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

    @Override
    public User getUserByAuthAttribute(AuthAttributeEnum name, String value) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(value);

        JinqStream<User> users = streams.streamAll(entityManager, User.class);
        JinqStream<Pair<User, AuthenticationAttribute>> pairs = users.join(u -> JinqStream.from(u.getAuthenticationAttributes()));

        return pairs.where(p -> p.getTwo().getName().equals(name)
                && p.getTwo().getValue().equals(value)).select(Pair::getOne).findOne().orElse(null);
    }

    @Override
    public List<User> getAllVerifiedMembers() {
        return streams.streamAll(entityManager, User.class).where(u -> u.getRole() != UserRole.CANDIDATE).toList();
    }

    @Override
    public long getVerifiedMemberCount() {
        return streams.streamAll(entityManager, User.class).where(u -> u.getRole() != UserRole.CANDIDATE).count();
    }
}
