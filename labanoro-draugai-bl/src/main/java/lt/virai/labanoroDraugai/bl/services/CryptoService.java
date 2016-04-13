package lt.virai.labanoroDraugai.bl.services;

/**
 * Created by Žilvinas on 2016-04-13.
 */
public interface CryptoService {
    String hashPassword(String password);

    boolean checkPassword(String candidate, String hashed);
}
