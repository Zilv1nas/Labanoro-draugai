package lt.virai.labanoroDraugai.domain.entities;

import lt.virai.labanoroDraugai.domain.model.AuthAttributeEnum;

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
 * Created by Žilvinas on 2016-04-12.
 */
@Entity
@Table(name = "authentication_attribute", schema = "labanoro-draugai-db", catalog = "")
public class AuthenticationAttribute {
    private Integer id;
    private AuthAttributeEnum name;
    private String value;

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
    @Column(name = "name", nullable = false, length = 64)
    public AuthAttributeEnum getName() {
        return name;
    }

    public void setName(AuthAttributeEnum name) {
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
        AuthenticationAttribute that = (AuthenticationAttribute) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, value);
    }
}
