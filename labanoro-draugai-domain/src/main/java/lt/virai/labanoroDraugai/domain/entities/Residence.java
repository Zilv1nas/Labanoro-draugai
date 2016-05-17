package lt.virai.labanoroDraugai.domain.entities;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Mantas on 4/13/2016.
 */
@Entity
@Table(name = "residence")
public class Residence {
    private Integer id;
    private String name;
    private String description;
    private Integer capacity;
    private String address;
    private City city;
    private String image;
    private Integer weeklyPrice;
    private LocalDate availableFrom;
    private LocalDate availableUntil;
    private Set<ExtraService> extraServices = new HashSet<>();

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

    @Basic
    @Column(name = "description", nullable = false, length = 2048)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Lob
    @Column(name = "image")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Basic
    @Column(name = "weekly_price", nullable = false)
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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "residence_id", nullable = false)
    public Set<ExtraService> getExtraServices() {
        return extraServices;
    }

    public void setExtraServices(Set<ExtraService> extraServices) {
        this.extraServices = extraServices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Residence residence = (Residence) o;
        return Objects.equals(id, residence.id) &&
                Objects.equals(name, residence.name) &&
                Objects.equals(description, residence.description) &&
                Objects.equals(capacity, residence.capacity) &&
                Objects.equals(address, residence.address) &&
                Objects.equals(city, residence.city) &&
                Objects.equals(image, residence.image) &&
                Objects.equals(weeklyPrice, residence.weeklyPrice) &&
                Objects.equals(availableFrom, residence.availableFrom) &&
                Objects.equals(availableUntil, residence.availableUntil);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, capacity, address, city, image, weeklyPrice, availableFrom, availableUntil);
    }
}
