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
    @Email(regexp = EMAIL_REGEX, message = "Blogas el. pašto formatas")
    private String email;
    @NotEmpty(message = "Vardas negali būti tuščias")
    private String name;
    @NotEmpty(message = "Pavardė negali būti tuščia")
    private String surname;
    @NotNull(message = "Slaptažodžio negali būti tuščias")
    @Size(min = 6, max = 20, message = "Slaptažodžio ilgis turi būti nuo 6 iki 20 simbolių")
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
