package lt.virai.labanoroDraugai.domain.entities;

import lt.virai.labanoroDraugai.domain.model.ClubSettingName;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/**
 * Created by Žilvinas on 2016-05-19.
 */
@Entity
@Table(name = "club_setting")
public class ClubSetting {
    private Integer id;
    private ClubSettingName name;
    private String value;

    public ClubSetting() {
    }

    public ClubSetting(ClubSettingName name, String value) {
        this.name = name;
        this.value = value;
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
    @Enumerated(EnumType.STRING)
    @Column(name = "name", unique = true, nullable = false, length = 128)
    public ClubSettingName getName() {
        return name;
    }

    public void setName(ClubSettingName name) {
        this.name = name;
    }

    @Basic
    @Column(name = "value", nullable = false, length = 1024)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClubSetting that = (ClubSetting) o;
        return Objects.equals(id, that.id) &&
                name == that.name &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, value);
    }
}
