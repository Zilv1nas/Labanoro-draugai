package lt.virai.labanoroDraugai.ui.model.purchases;

import lt.virai.labanoroDraugai.domain.entities.PointPurchase;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;

import static lt.virai.labanoroDraugai.ui.util.ValidationUtils.EMAIL_REGEX;

/**
 * Created by Žilvinas on 2016-05-10.
 */
public class PointPurchaseModel {
    private Integer id;
    private String name;
    private String surname;
    @Email(regexp = EMAIL_REGEX)
    private String username;
    @Range(min = 0)
    private Integer amount;
    private LocalDateTime purchaseDate;
    private String status;

    public PointPurchaseModel() {
    }

    public PointPurchaseModel(Integer id, String name, String surname, String username, Integer amount, LocalDateTime purchaseDate, String status) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.amount = amount;
        this.purchaseDate = purchaseDate;
        this.status = status;
    }

    public PointPurchaseModel(PointPurchase pp) {
        this.id = pp.getId();
        this.name = pp.getUser().getName();
        this.surname = pp.getUser().getSurname();
        this.username = pp.getUser().getEmail();
        this.amount = pp.getAmount();
        this.status = pp.getStatus().name();
        this.purchaseDate = pp.getPurchaseDate();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
