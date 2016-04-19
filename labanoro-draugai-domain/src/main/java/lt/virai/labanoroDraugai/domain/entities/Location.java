package lt.virai.labanoroDraugai.domain.entities;

import javax.persistence.*;

/**
 * Created by Mantas on 4/13/2016.
 */
@Entity
@Table(name="location", schema = "labanoro-draugai-db", catalog = "")
public class Location {
    private Integer id;
    private String address;
    private City city;

    public Location(City city, String address) {
        this.city = city;
        this.address = address;
    }

    public Location(String address) {

        this.address = address;
    }

    @Basic
    @Column(name="address", nullable = false)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @OneToOne
    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
