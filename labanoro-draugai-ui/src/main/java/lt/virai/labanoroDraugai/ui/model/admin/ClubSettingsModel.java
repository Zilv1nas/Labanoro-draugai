package lt.virai.labanoroDraugai.ui.model.admin;

import lt.virai.labanoroDraugai.domain.entities.ClubSetting;
import lt.virai.labanoroDraugai.domain.model.ClubSettingName;
import lt.virai.labanoroDraugai.ui.model.MappableTo;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Žilvinas on 2016-05-19.
 */
public class ClubSettingsModel implements MappableTo<List<ClubSetting>> {
    @NotNull
    @Range(min = 1)
    private Integer maxClubMembers;
    @NotNull
    @Range(min = 0)
    private Integer annualPaymentSIze;

    public ClubSettingsModel() {
    }

    public ClubSettingsModel(List<ClubSetting> settings) {
        settings.forEach(s -> {
            if (s.getName() == ClubSettingName.MAX_MEMBERS) {
                maxClubMembers = Integer.parseInt(s.getValue());
            } else if (s.getName() == ClubSettingName.ANNUAL_PAYMENT_SIZE) {
                annualPaymentSIze = Integer.parseInt(s.getValue());
            }
        });
    }

    public Integer getMaxClubMembers() {
        return maxClubMembers;
    }

    public void setMaxClubMembers(Integer maxClubMembers) {
        this.maxClubMembers = maxClubMembers;
    }

    public Integer getAnnualPaymentSIze() {
        return annualPaymentSIze;
    }

    public void setAnnualPaymentSIze(Integer annualPaymentSIze) {
        this.annualPaymentSIze = annualPaymentSIze;
    }

    @Override
    public List<ClubSetting> mapTo() {
        List<ClubSetting> clubSettings = new ArrayList<>();
        ClubSetting maxClubMembersEntity;
        if (maxClubMembers != null) {
            maxClubMembersEntity = new ClubSetting(ClubSettingName.MAX_MEMBERS, maxClubMembers.toString());
            clubSettings.add(maxClubMembersEntity);
        }
        if (annualPaymentSIze != null) {
            maxClubMembersEntity = new ClubSetting(ClubSettingName.ANNUAL_PAYMENT_SIZE, annualPaymentSIze.toString());
            clubSettings.add(maxClubMembersEntity);
        }
        return clubSettings;
    }
}
