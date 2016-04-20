package lt.virai.labanoroDraugai.domain.entities;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by Mantas on 4/13/2016.
 */
@Entity
@Table(name="city")
public class City {
    private Integer id;
    private String name;

    public City(String name) {
        this.name = name;
    }

    protected City() {
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
    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(id, city.id) &&
                Objects.equals(name, city.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
