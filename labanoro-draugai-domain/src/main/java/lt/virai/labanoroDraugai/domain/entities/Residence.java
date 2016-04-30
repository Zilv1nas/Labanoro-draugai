package lt.virai.labanoroDraugai.domain.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Created by Mantas on 4/13/2016.
 */
@Entity
@Table(name = "residence")
public class Residence {
    private Integer id;
    private Integer capacity;
    private String address;
    private City city;
    private String image;
    private Integer weeklyPrice;
    private LocalDate availableFrom;
    private LocalDate availableUntil;

    protected Residence() {
    }

    public Residence(Integer capacity, String address, City city, Integer weeklyPrice, LocalDate availableFrom) {
        this.capacity = capacity;
        this.address = address;
        this.city = city;
        this.weeklyPrice = weeklyPrice;
        this.availableFrom = availableFrom;
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
    @Column(name = "capacity", nullable = false)
    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    @Basic
    @Column(name = "address", nullable = false)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Residence residence = (Residence) o;
        return Objects.equals(id, residence.id) &&
                Objects.equals(capacity, residence.capacity) &&
                Objects.equals(address, residence.address) &&
                Objects.equals(city, residence.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, capacity, address, city);
    }

    @Lob
    @Column(name = "image")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Basic
    @Column(name = "address", nullable = false)
    public Integer getWeeklyPrice() {
        return weeklyPrice;
    }

    public void setWeeklyPrice(Integer weeklyPrice) {
        this.weeklyPrice = weeklyPrice;
    }

    @Basic
    @Column(name = "available_from", nullable = false)
    public LocalDate getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(LocalDate availableFrom) {
        this.availableFrom = availableFrom;
    }

    @Basic
    @Column(name = "available_until", nullable = false)
    public LocalDate getAvailableUntil() {
        return availableUntil;
    }

    public void setAvailableUntil(LocalDate availableUntil) {
        this.availableUntil = availableUntil;
    }
}
