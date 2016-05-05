package lt.virai.labanoroDraugai.ui.model.auth;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static lt.virai.labanoroDraugai.ui.util.ValidationUtils.EMAIL_REGEX;

/**
 * Created by Žilvinas on 2016-04-17.
 */
public class RegistrationInfo {
    @Email(regexp = EMAIL_REGEX)
    private String email;
    @NotEmpty
    private String name;
    @NotEmpty
    private String surname;
    @NotNull
    @Size(min = 2, max = 50)
    private String password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
