package lt.virai.labanoroDraugai.ui.util;

/**
 * Created by Žilvinas on 2016-04-17.
 */
public class AuthUtils {

    private static final String AUTH_HEADER_PREFIX = "Bearer ";

    public static String extractToken(String authenticationHeader) {
        String token;
        if (authenticationHeader == null || authenticationHeader.isEmpty() || !authenticationHeader.startsWith(AUTH_HEADER_PREFIX)) {
            token = null;
        } else {
            token = authenticationHeader.substring(AUTH_HEADER_PREFIX.length() - 1).trim();
        }

        return token;
    }
}
