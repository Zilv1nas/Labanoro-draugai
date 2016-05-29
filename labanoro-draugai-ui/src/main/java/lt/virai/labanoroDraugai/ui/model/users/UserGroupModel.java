package lt.virai.labanoroDraugai.ui.model.users;

import lt.virai.labanoroDraugai.domain.model.UserGroupSettings;
import lt.virai.labanoroDraugai.ui.model.MappableTo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by Mantas on 5/29/2016.
 */
public class UserGroupModel implements MappableTo<UserGroupSettings> {
    @NotNull(message = "Grupių skaičius būti tušias")
    @Min(value = 2, message = "Grupių skaičius negali būti mažesnis nei 2")
    private Integer groupsNumber;

    @NotNull(message = "Dienų intervalas negali būti tušias")
    @Min(value = 1, message = "Dienų intervalas negali būti mažesnis nei 1")
    private Integer daysInterval;

    public UserGroupModel() {
    }

    public UserGroupModel(UserGroupSettings settings) {
        groupsNumber = settings.getGroupsNumber();

        daysInterval = settings.getDaysInterval();
    }

    public Integer getGroupsNumber() {
        return groupsNumber;
    }

    public void setGroupsNumber(Integer groupsNumber) {
        this.groupsNumber = groupsNumber;
    }

    public Integer getDaysInterval() {
        return daysInterval;
    }

    public void setDaysInterval(Integer daysInterval) {
        this.daysInterval = daysInterval;
    }

    @Override
    public UserGroupSettings mapTo() {
        return new UserGroupSettings(groupsNumber, daysInterval);
    }
}
