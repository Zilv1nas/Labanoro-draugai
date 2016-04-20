package lt.virai.labanoroDraugai.domain.entities;

import javax.persistence.*;

/**
 * Created by Mantas on 4/13/2016.
 */
@Entity
@Table(name = "residence", schema = "labanoro-draugai-db", catalog = "")
public class Residence {
    private Integer id;
    private Integer capacity;
    private String address;
    private City city;

    protected Residence() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public Residence(Integer capacity, String address, City city) {
        this.capacity = capacity;
        this.address = address;
        this.city = city;
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

    @Basic
    @Column(name="address", nullable = false)
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    @OneToOne
    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
