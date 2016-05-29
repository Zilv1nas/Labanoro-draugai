package lt.virai.labanoroDraugai.bl.services;

import lt.virai.labanoroDraugai.domain.model.UserGroupSettings;

/**
 * Created by Mantas on 5/29/2016.
 */
public interface GroupService {
    UserGroupSettings getGroupSettings();

    void updateGroups(UserGroupSettings settings);
}
