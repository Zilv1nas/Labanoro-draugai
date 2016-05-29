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
    @NotNull(message = "Negali būti tuščias")
    @Range(min = 1, message = "Minimalus narių skaičius yra 1")
    private Integer maxClubMembers;
    @NotNull(message = "Negali būti tuščias")
    @Range(min = 0, message = "Metinis mokestis negali būti neigiamas")
    private Integer annualPaymentSize;
    @NotNull(message = "Negali būti tuščias")
    @Range(min = 2, message = "Minimalus rekomendacijų skaičius: 2")
    private Integer recommendationCount;

    public ClubSettingsModel() {
    }

    public ClubSettingsModel(List<ClubSetting> settings) {
        settings.forEach(s -> {
            if (s.getName() == ClubSettingName.MAX_MEMBERS) {
                maxClubMembers = Integer.parseInt(s.getValue());
            } else if (s.getName() == ClubSettingName.ANNUAL_PAYMENT_SIZE) {
                annualPaymentSize = Integer.parseInt(s.getValue());
            } else if (s.getName() == ClubSettingName.RECOMMENDATION_COUNT) {
                recommendationCount= Integer.parseInt(s.getValue());
            }
        });
    }

    public Integer getMaxClubMembers() {
        return maxClubMembers;
    }

    public void setMaxClubMembers(Integer maxClubMembers) {
        this.maxClubMembers = maxClubMembers;
    }

    public Integer getAnnualPaymentSize() {
        return annualPaymentSize;
    }

    public void setAnnualPaymentSize(Integer annualPaymentSize) {
        this.annualPaymentSize = annualPaymentSize;
    }

    public Integer getRecommendationCount() {
        return recommendationCount;
    }

    public void setRecommendationCount(Integer recommendationCount) {
        this.recommendationCount = recommendationCount;
    }

    @Override
    public List<ClubSetting> mapTo() {
        List<ClubSetting> clubSettings = new ArrayList<>();
        ClubSetting entity;
        if (maxClubMembers != null) {
            entity = new ClubSetting(ClubSettingName.MAX_MEMBERS, maxClubMembers.toString());
            clubSettings.add(entity);
        }
        if (annualPaymentSize != null) {
            entity = new ClubSetting(ClubSettingName.ANNUAL_PAYMENT_SIZE, annualPaymentSize.toString());
            clubSettings.add(entity);
        }
        if (recommendationCount != null) {
            entity = new ClubSetting(ClubSettingName.RECOMMENDATION_COUNT, recommendationCount.toString());
            clubSettings.add(entity);
        }

        return clubSettings;
    }
}
