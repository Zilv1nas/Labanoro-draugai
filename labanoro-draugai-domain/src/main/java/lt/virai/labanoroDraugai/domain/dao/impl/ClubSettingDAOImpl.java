package lt.virai.labanoroDraugai.domain.dao.impl;

import lt.virai.labanoroDraugai.domain.dao.AbstractDAO;
import lt.virai.labanoroDraugai.domain.dao.ClubSettingDAO;
import lt.virai.labanoroDraugai.domain.entities.ClubSetting;
import lt.virai.labanoroDraugai.domain.model.ClubSettingName;

import java.util.Objects;

/**
 * Created by Žilvinas on 2016-05-19.
 */
public class ClubSettingDAOImpl extends AbstractDAO<ClubSetting> implements ClubSettingDAO {

    @Override
    public ClubSetting getSetting(ClubSettingName name) {
        Objects.requireNonNull(name);
        return streams.streamAll(entityManager, ClubSetting.class).where(s -> s.getName() == name).findOne().orElse(null);
    }
}
