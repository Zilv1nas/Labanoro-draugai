package lt.virai.labanoroDraugai.bl.services.impl;

import lt.virai.labanoroDraugai.bl.services.CryptoService;
import org.mindrot.jbcrypt.BCrypt;

import javax.ejb.Stateless;

/**
 * Created by Žilvinas on 2016-04-13.
 */
@Stateless
public class BcryptService implements CryptoService {

    private static final int SALT_LOG_ROUNDS = 12;

    @Override
    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(SALT_LOG_ROUNDS));
    }

    @Override
    public boolean checkPassword(String candidate, String hashed) {
        return BCrypt.checkpw(candidate, hashed);
    }
}
