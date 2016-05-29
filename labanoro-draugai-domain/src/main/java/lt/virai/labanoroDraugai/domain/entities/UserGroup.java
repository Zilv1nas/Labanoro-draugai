package lt.virai.labanoroDraugai.domain.entities;

import javax.persistence.*;

/**
 * Created by Mantas on 5/29/2016.
 */
public class UserGroup {
    private Integer id;
    private Integer daysRequirement;
    private Integer priority;

    public UserGroup(Integer id, Integer priority) {
        this.id = id;
        this.priority = priority;
    }

    public UserGroup() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "days_requirement", nullable = true)
    public Integer getDaysRequirement() {
        return daysRequirement;
    }

    public void setDaysRequirement(Integer daysRequirement) {
        this.daysRequirement = daysRequirement;
    }

    @Basic
    @Column(name = "priority", nullable = false)
    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
