package lt.virai.labanoroDraugai.domain.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Created by Žilvinas on 2016-05-19.
 */
@Entity
@Table(name = "annual_payment")
public class AnnualPayment {
    private Integer id;
    private Integer amount;
    private LocalDate paymentDate;
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "amount", nullable = false)
    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "payment_date", nullable = false)
    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnnualPayment that = (AnnualPayment) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(paymentDate, that.paymentDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, paymentDate);
    }
}
