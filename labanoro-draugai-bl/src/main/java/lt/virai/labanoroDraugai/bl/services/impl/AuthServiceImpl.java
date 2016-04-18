package lt.virai.labanoroDraugai.bl.services.impl;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import lt.virai.labanoroDraugai.bl.model.AuthResult;
import lt.virai.labanoroDraugai.bl.services.AuthService;
import lt.virai.labanoroDraugai.bl.services.CryptoService;
import lt.virai.labanoroDraugai.domain.dao.UserDAO;
import lt.virai.labanoroDraugai.domain.entities.AuthenticationAttribute;
import lt.virai.labanoroDraugai.domain.entities.User;
import lt.virai.labanoroDraugai.domain.model.AuthAttributeEnum;
import lt.virai.labanoroDraugai.domain.model.UserRole;

import javax.crypto.spec.SecretKeySpec;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.security.sasl.AuthenticationException;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.List;
import java.util.Objects;

/**
 * Created by Žilvinas on 2016-04-13.
 */
@Stateless
public class AuthServiceImpl implements AuthService {

    @Inject
    private UserDAO userDAO;

    @Inject
    private CryptoService cryptoService;

    private static final String ROLE_CLAIM = "role";

    private static final String SECRET = "virai.lt"; //TODO put to properties

    private static final Key signingKey = new SecretKeySpec(DatatypeConverter.parseBase64Binary(SECRET), SignatureAlgorithm.HS512.getJcaName());

    @Override
    public AuthResult login(String username, String password) throws AuthenticationException {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        User user = userDAO.getByUsername(username);

        if (user == null) {
            throw new AuthenticationException("Wrong username or password");
        }

        AuthenticationAttribute attr = user.getAuthenticationAttributes().stream()
                .filter(a -> a.getName().equals(AuthAttributeEnum.PASSWORD))
                .findAny()
                .orElse(null);

        if (attr != null && cryptoService.checkPassword(password, attr.getValue())) {
            return new AuthResult(user, issueToken(user));
        } else {
            throw new AuthenticationException("Wrong username or password");
        }
    }

    @Override
    public boolean isAuthorized(String token) {
        try {
            Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            return false;
        }
    }

    @Override
    public boolean hasRoles(String token, List<UserRole> userRoles) {
        Objects.requireNonNull(token);
        Objects.requireNonNull(userRoles);

        try {
            String userRole = (String) Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody().get(ROLE_CLAIM);
            return userRoles.isEmpty() || userRoles.contains(UserRole.valueOf(userRole));
        } catch (SignatureException e) {
            return false;
        }
    }

    @Override
    public AuthResult register(User user) {
        Objects.requireNonNull(user);

        if (userDAO.getByUsername(user.getEmail()) != null) {
            throw new IllegalArgumentException("User already exists");
        }

        AuthenticationAttribute password = user.getAuthenticationAttributes().stream()
                .filter(a -> a.getName().equals(AuthAttributeEnum.PASSWORD))
                .findAny()
                .orElse(null);

        password.setValue(cryptoService.hashPassword(password.getValue()));

        userDAO.save(user);

        return new AuthResult(user, issueToken(user));
    }

    private String issueToken(User user) {
        return Jwts.builder()
                .setSubject(user.getId().toString())
                .claim(ROLE_CLAIM, user.getRole().toString())
                .signWith(SignatureAlgorithm.HS512, signingKey)
                .compact();
    }
}
