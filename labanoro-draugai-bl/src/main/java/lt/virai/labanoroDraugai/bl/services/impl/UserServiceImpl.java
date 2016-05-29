package lt.virai.labanoroDraugai.bl.services.impl;

import lt.virai.labanoroDraugai.bl.services.UserService;
import lt.virai.labanoroDraugai.domain.dao.ClubSettingDAO;
import lt.virai.labanoroDraugai.domain.dao.UserDAO;
import lt.virai.labanoroDraugai.domain.dao.UserGroupDAO;
import lt.virai.labanoroDraugai.domain.entities.User;
import lt.virai.labanoroDraugai.domain.model.ClubSettingName;
import lt.virai.labanoroDraugai.domain.model.UserRole;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import static java.lang.Math.toIntExact;

/**
 * Created by Žilvinas on 2016-03-11.
 */
@Stateless
public class UserServiceImpl implements UserService {

    @Inject
    private UserDAO userDAO;

    @Inject
    private UserGroupDAO userGroupDAO;

    @Inject
    private ClubSettingDAO clubSettingDAO;


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
    public void verifyUser(int recommenderId, int recommendeeId) {
        int recommendationCount = Optional.ofNullable(clubSettingDAO.getSetting(ClubSettingName.RECOMMENDATION_COUNT))
                .map(s -> Integer.parseInt(s.getValue()))
                .orElseThrow(IllegalStateException::new);

        Optional.ofNullable(userDAO.get(recommendeeId)).ifPresent(u -> {
            if (!UserRole.CANDIDATE.equals(u.getRole())) {
                return;
            }

            User asd = userDAO.get(recommenderId);
            asd.getRecommendations().add(u);
            u.getRecommenders().add(asd);

            if (u.getRecommenders().size() >= recommendationCount) {
                u.setRole(UserRole.MEMBER);
                u.setUserGroup(userGroupDAO.getFirstUserGroup());
            }
            userDAO.update(u);
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

    @Override
    public boolean emailExists(String email) {
        return email != null && !email.isEmpty() && userDAO.emailExists(email);
    }

    @Override
    public int getRecommendersCount(Integer userId) {
        return toIntExact(userDAO.getRecommendersCount(Objects.requireNonNull(userId)));
    }

    @Override
    public boolean isRecommendedBy(int userId, int recommendedByUserId) {
        return userDAO.isRecommendedBy(userId, recommendedByUserId);
    }
}
