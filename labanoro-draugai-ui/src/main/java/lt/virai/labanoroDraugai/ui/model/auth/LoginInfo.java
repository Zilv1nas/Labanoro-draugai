package lt.virai.labanoroDraugai.ui.model.auth;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static lt.virai.labanoroDraugai.ui.util.ValidationUtils.EMAIL_REGEX;

/**
 * Created by Žilvinas on 2016-04-12.
 */
public class LoginInfo {
    @Email(regexp = EMAIL_REGEX, message = "Blogas el. pašto formatas")
    private String username;

    @NotNull(message = "Slaptažodžio negali būti tuščias")
    @Size(min = 6, max = 20, message = "Slaptažodžio ilgis turi būti nuo 6 iki 20 simbolių")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
