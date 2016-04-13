package lt.virai.labanoroDraugai.bl.services;

import lt.virai.labanoroDraugai.bl.model.AuthResult;

import javax.security.sasl.AuthenticationException;

/**
 * Created by Žilvinas on 2016-04-13.
 */
public interface AuthService {

    AuthResult login(String username, String password) throws AuthenticationException;
}
