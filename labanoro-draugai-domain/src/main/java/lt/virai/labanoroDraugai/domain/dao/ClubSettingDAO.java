package lt.virai.labanoroDraugai.domain.dao;

import lt.virai.labanoroDraugai.domain.entities.ClubSetting;
import lt.virai.labanoroDraugai.domain.model.ClubSettingName;

/**
 * Created by Žilvinas on 2016-05-19.
 */
public interface ClubSettingDAO extends DAO<ClubSetting> {
    ClubSetting getSetting(ClubSettingName name);
}
