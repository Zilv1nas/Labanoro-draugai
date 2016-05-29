package lt.virai.labanoroDraugai.domain.model;

/**
 * Created by Mantas on 5/29/2016.
 */
public class UserGroupSettings {
    private Integer groupsNumber;
    private Integer daysInterval;

    public UserGroupSettings(Integer groupsNumber, Integer daysInterval) {
        this.groupsNumber = groupsNumber;
        this.daysInterval = daysInterval;
    }

    public Integer getGroupsNumber() {
        return groupsNumber;
    }

    public Integer getDaysInterval() {
        return daysInterval;
    }
}
