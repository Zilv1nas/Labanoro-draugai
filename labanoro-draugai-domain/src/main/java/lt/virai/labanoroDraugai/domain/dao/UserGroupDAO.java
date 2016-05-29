package lt.virai.labanoroDraugai.domain.dao;

import lt.virai.labanoroDraugai.domain.entities.User;
import lt.virai.labanoroDraugai.domain.entities.UserGroup;

/**
 * Created by Mantas on 5/29/2016.
 */
public interface UserGroupDAO extends DAO<UserGroup> {
    UserGroup getFirstUserGroup();
    UserGroup getLastUserGroup();
}
