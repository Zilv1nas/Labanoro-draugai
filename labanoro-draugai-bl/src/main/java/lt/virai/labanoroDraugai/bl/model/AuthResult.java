package lt.virai.labanoroDraugai.bl.model;

import lt.virai.labanoroDraugai.domain.entities.User;

/**
 * Created by Žilvinas on 2016-04-13.
 */
public class AuthResult {

    private String email;

    private String name;

    private String surname;

    private String role;

    private String token;

    public AuthResult(User user, String token) {
        this.email = user.getEmail();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.role = user.getRole().toString();
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getRole() {
        return role;
    }

    public String getToken() {
        return token;
    }
}
