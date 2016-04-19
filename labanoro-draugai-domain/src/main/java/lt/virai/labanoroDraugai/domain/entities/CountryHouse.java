package lt.virai.labanoroDraugai.domain.entities;

import javax.persistence.*;

/**
 * Created by Mantas on 4/13/2016.
 */
@Entity
@Table(name = "country_house", schema = "labanoro-draugai-db", catalog = "")
public class CountryHouse {
    private Integer id;
    private Integer capacity;
    private Location location;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public CountryHouse(Integer capacity, Location location) {
        this.capacity = capacity;
        this.location = location;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "capacity", nullable = false)
    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}
