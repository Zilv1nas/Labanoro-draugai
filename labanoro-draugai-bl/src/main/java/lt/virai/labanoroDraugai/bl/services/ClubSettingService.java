package lt.virai.labanoroDraugai.bl.services;

import lt.virai.labanoroDraugai.domain.entities.ClubSetting;

import java.util.List;

/**
 * Created by Žilvinas on 2016-05-19.
 */
public interface ClubSettingService {
    List<ClubSetting> getAllSettings();

    void updateClubSettings(List<ClubSetting> settings);

    boolean isMemberCapacityExceeded();

    int getAnnualPaymentSize();

    int getRequiredConfirmationCount();
}
