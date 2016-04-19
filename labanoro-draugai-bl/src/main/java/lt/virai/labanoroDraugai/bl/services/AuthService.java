package lt.virai.labanoroDraugai.bl.services;

import lt.virai.labanoroDraugai.bl.model.AuthResult;
import lt.virai.labanoroDraugai.domain.entities.User;
import lt.virai.labanoroDraugai.domain.model.UserRole;

import javax.security.sasl.AuthenticationException;
import java.util.List;

/**
 * Created by Žilvinas on 2016-04-13.
 */
public interface AuthService {
    AuthResult login(String username, String password) throws AuthenticationException;

    boolean isAuthorized(String token);

    boolean hasRoles(String token, List<UserRole> userRoles);

    AuthResult register(User user);
}
