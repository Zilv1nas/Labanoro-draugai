package lt.virai.labanoroDraugai.bl.model;

import lt.virai.labanoroDraugai.domain.entities.User;

/**
 * Created by Žilvinas on 2016-04-13.
 */
public class AuthResult {

    private User user;

    private String token;

    public AuthResult(User user, String token) {
        this.user = user;
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public String getToken() {
        return token;
    }
}
