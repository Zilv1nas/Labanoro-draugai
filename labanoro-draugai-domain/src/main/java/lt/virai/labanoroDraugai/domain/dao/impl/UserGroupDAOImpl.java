package lt.virai.labanoroDraugai.domain.dao.impl;

import lt.virai.labanoroDraugai.domain.dao.AbstractDAO;
import lt.virai.labanoroDraugai.domain.dao.UserGroupDAO;
import lt.virai.labanoroDraugai.domain.entities.UserGroup;

import javax.ejb.Stateless;

/**
 * Created by Mantas on 5/29/2016.
 */
@Stateless
public class UserGroupDAOImpl extends AbstractDAO<UserGroup> implements UserGroupDAO {
    @Override
    public UserGroup getFirstUserGroup() {
        return getAll().stream().filter(a -> a.getDaysRequirement() != null).sorted((e1, e2) ->
                e1.getPriority().compareTo(e2.getPriority())).findFirst().get();
    }

    @Override
    public UserGroup getLastUserGroup() {
        return getAll().stream().filter(a -> a.getDaysRequirement() != null).findFirst().get();
    }
}
