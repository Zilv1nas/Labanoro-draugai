package lt.virai.labanoroDraugai.domain.entities;

import lt.virai.labanoroDraugai.domain.model.UserRole;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Žilvinas on 2016-03-11.
 */

@Entity
@Table(name = "user")
public class User {
    private Integer id;
    private String email;
    private String name;
    private String surname;
    private Integer balance = 0;
    private UserRole role = UserRole.CANDIDATE;
    private LocalDate registrationDate = LocalDate.now();
    private Set<AuthenticationAttribute> authenticationAttributes = new HashSet<>();
    private Set<AnnualPayment> annualPayments = new HashSet<>();
    private Set<User> recommendations = new HashSet<>();
    private Set<User> recommenders = new HashSet<>();
    private UserGroup userGroup;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 64)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "registration_date", nullable = false)
    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    public Set<AuthenticationAttribute> getAuthenticationAttributes() {
        return authenticationAttributes;
    }

    public void setAuthenticationAttributes(Set<AuthenticationAttribute> authenticationAttributes) {
        this.authenticationAttributes = authenticationAttributes;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 32)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "surname", nullable = false, length = 32)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 128)
    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Basic
    @Column(name = "balance", nullable = false)
    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<AnnualPayment> getAnnualPayments() {
        return annualPayments;
    }

    public void setAnnualPayments(Set<AnnualPayment> annualPayments) {
        this.annualPayments = annualPayments;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "recommendee_recommender",
            joinColumns = @JoinColumn(name = "recommendee_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "recommender_id", referencedColumnName = "id"))
    public Set<User> getRecommenders() {
        return recommenders;
    }

    public void setRecommenders(Set<User> recommenders) {
        this.recommenders = recommenders;
    }

    @ManyToMany(mappedBy = "recommenders",fetch = FetchType.LAZY)
    public Set<User> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(Set<User> recommendations) {
        this.recommendations = recommendations;
    }

    @ManyToOne
    @JoinColumn(name = "user_group_id", nullable = true)
    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(email, user.email) &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(balance, user.balance) &&
                role == user.role &&
                Objects.equals(registrationDate, user.registrationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, name, surname, balance, role, registrationDate);
    }
}
