package lt.virai.labanoroDraugai.bl.services.impl;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lt.virai.labanoroDraugai.bl.model.AuthResult;
import lt.virai.labanoroDraugai.bl.services.AuthService;
import lt.virai.labanoroDraugai.bl.services.CryptoService;
import lt.virai.labanoroDraugai.domain.dao.UserDAO;
import lt.virai.labanoroDraugai.domain.entities.AuthenticationAttribute;
import lt.virai.labanoroDraugai.domain.entities.User;

import javax.crypto.spec.SecretKeySpec;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.security.sasl.AuthenticationException;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

/**
 * Created by Žilvinas on 2016-04-13.
 */
@Stateless
public class AuthServiceImpl implements AuthService {

    @Inject
    private UserDAO userDAO;

    @Inject
    private CryptoService cryptoService;

    private static final String SECRET = "virai.lt"; //TODO put to properties

    @Override
    public AuthResult login(String username, String password) throws AuthenticationException {
        User user = userDAO.getUserFromLogin(username, cryptoService.hashPassword(password));

        if (user == null) {
            throw new AuthenticationException("Wrong username or password");
        }

        return new AuthResult(user, issueToken(user));
    }

    private String issueToken(User user) {
        Key signingKey = new SecretKeySpec(DatatypeConverter.parseBase64Binary(SECRET), SignatureAlgorithm.HS512.getJcaName());
        return Jwts.builder().setSubject(user.getId().toString()).signWith(SignatureAlgorithm.HS512, signingKey).compact();
    }
}
