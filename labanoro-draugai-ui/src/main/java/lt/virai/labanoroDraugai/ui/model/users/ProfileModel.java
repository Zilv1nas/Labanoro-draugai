package lt.virai.labanoroDraugai.ui.model.users;

import lt.virai.labanoroDraugai.domain.entities.User;
import lt.virai.labanoroDraugai.domain.model.UserRole;
import lt.virai.labanoroDraugai.ui.model.MappableTo;
import lt.virai.labanoroDraugai.ui.util.ValidationUtils;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Created by Žilvinas on 2016-04-21.
 */
public class ProfileModel implements MappableTo<User> {
    @NotNull(message = "Vartotojo id yra privalomas")
    private Integer id;

    @Email(regexp = ValidationUtils.EMAIL_REGEX, message = "Blogas el. pašto formatas")
    private String email;

    @NotEmpty(message = "Vardas negali būti tuščias")
    private String name;

    @NotEmpty(message = "Pavardė negali būti tuščia")
    private String surname;

    @NotNull(message = "Registracijos data negali būti tuščia")
    private LocalDate registrationDate;

    private String role;

    private Integer balance;

    /*Mapped externally*/
    private LocalDate lastPaymentDate;

    /*Mapped externally*/
    private Integer annualPaymentSize;

    /*Mapped externally*/
    private Integer confirmationCount;

    /*Mapped externally*/
    private Integer requiredConfirmationCount;

    public ProfileModel() {
    }

    public ProfileModel(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.registrationDate = user.getRegistrationDate();
        this.role = user.getRole().toString();
        this.balance = user.getBalance();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public LocalDate getLastPaymentDate() {
        return lastPaymentDate;
    }

    public void setLastPaymentDate(LocalDate lastPaymentDate) {
        this.lastPaymentDate = lastPaymentDate;
    }

    public Integer getAnnualPaymentSize() {
        return annualPaymentSize;
    }

    public void setAnnualPaymentSize(Integer annualPaymentSize) {
        this.annualPaymentSize = annualPaymentSize;
    }

    public Integer getConfirmationCount() {
        return confirmationCount;
    }

    public void setConfirmationCount(Integer confirmationCount) {
        this.confirmationCount = confirmationCount;
    }

    public Integer getRequiredConfirmationCount() {
        return requiredConfirmationCount;
    }

    public void setRequiredConfirmationCount(Integer requiredConfirmationCount) {
        this.requiredConfirmationCount = requiredConfirmationCount;
    }

    @Override
    public User mapTo() {
        User user = new User();
        user.setId(id);
        user.setEmail(email);
        user.setName(name);
        user.setSurname(surname);
        user.setBalance(balance);
        user.setRole(UserRole.valueOf(role));
        user.setRegistrationDate(registrationDate);

        return user;
    }
}
