package lt.virai.labanoroDraugai.bl.services.impl;

import lt.virai.labanoroDraugai.bl.services.ClubSettingService;
import lt.virai.labanoroDraugai.domain.dao.ClubSettingDAO;
import lt.virai.labanoroDraugai.domain.dao.UserDAO;
import lt.virai.labanoroDraugai.domain.entities.ClubSetting;
import lt.virai.labanoroDraugai.domain.model.ClubSettingName;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by Žilvinas on 2016-05-19.
 */
@Stateless
public class ClubSettingServiceImpl implements ClubSettingService {

    @Inject
    private ClubSettingDAO clubSettingDAO;

    @Inject
    private UserDAO userDAO;

    @Override
    public List<ClubSetting> getAllSettings() {
        return clubSettingDAO.getAll();
    }

    @Override
    public void updateClubSettings(List<ClubSetting> settings) {
        Objects.requireNonNull(settings);

        settings.forEach(s -> {
            ClubSetting setting = clubSettingDAO.getSetting(s.getName());
            setting.setValue(s.getValue());
            clubSettingDAO.update(setting);
        });
    }

    @Override
    public boolean isMemberCapacityExceeded() {
        long memberCount = userDAO.getVerifiedMemberCount();
        long maxClubMembers = Long.parseLong(clubSettingDAO.getSetting(ClubSettingName.MAX_MEMBERS).getValue());

        return memberCount >= maxClubMembers;
    }

    @Override
    public int getAnnualPaymentSize() {
        return getIntegerSetting(ClubSettingName.ANNUAL_PAYMENT_SIZE);
    }

    @Override
    public int getRequiredConfirmationCount() {
        return getIntegerSetting(ClubSettingName.RECOMMENDATION_COUNT);
    }

    private int getIntegerSetting(ClubSettingName name) {
        return Optional.ofNullable(clubSettingDAO.getSetting(name))
                .map(s -> Integer.parseInt(s.getValue()))
                .orElseThrow(() -> new IllegalStateException(name + " setting not found!"));
    }
}
