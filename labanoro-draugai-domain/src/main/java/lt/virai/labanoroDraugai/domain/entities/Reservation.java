package lt.virai.labanoroDraugai.domain.entities;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by Mantas on 4/30/2016.
 */

@Entity
@Table(name = "reservation")
public class Reservation {
    private Integer id;
    private User user;
    private Residence residence;
    private LocalDate dateFrom;
    private LocalDate dateTo;

    public Reservation(User user, Residence residence, LocalDate dateFrom, LocalDate dateTo) {
        this.user = user;
        this.residence = residence;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    @OneToOne
    @Column(name = "user", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToOne
    @Column(name = "residence", nullable = false)
    public Residence getResidence() {
        return residence;
    }

    public void setResidence(Residence residence) {
        this.residence = residence;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
