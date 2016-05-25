package lt.virai.labanoroDraugai.domain.entities;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Mantas on 4/30/2016.
 */

@Entity
@Table(name = "reservation")
public class Reservation {
    private Integer id;
    private User user;
    private Residence residence;
    private Integer amountSpent;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private Set<ExtraService> selectedExtraServices = new HashSet<>();

    public Reservation(User user, Residence residence, LocalDate dateFrom, LocalDate dateTo) {
        this.user = user;
        this.residence = residence;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public Reservation() {
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
    @JoinColumn(name = "user_id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "residence_id", nullable = false)
    public Residence getResidence() {
        return residence;
    }

    public void setResidence(Residence residence) {
        this.residence = residence;
    }

    @Basic
    @Column(name = "amount_spent", nullable = false)
    public Integer getAmountSpent() {
        return amountSpent;
    }

    public void setAmountSpent(Integer amountSpent) {
        this.amountSpent = amountSpent;
    }

    @Basic
    @Column(name = "date_from", nullable = false)
    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    @Basic
    @Column(name = "date_to", nullable = false)
    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "reservation_extra_service",
            joinColumns = @JoinColumn(name = "reservation_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "extra_service_id", referencedColumnName = "id"))
    public Set<ExtraService> getSelectedExtraServices() {
        return selectedExtraServices;
    }

    public void setSelectedExtraServices(Set<ExtraService> selectedExtraServices) {
        this.selectedExtraServices = selectedExtraServices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(user, that.user) &&
                Objects.equals(residence, that.residence) &&
                Objects.equals(dateFrom, that.dateFrom) &&
                Objects.equals(dateTo, that.dateTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, residence, dateFrom, dateTo);
    }
}
