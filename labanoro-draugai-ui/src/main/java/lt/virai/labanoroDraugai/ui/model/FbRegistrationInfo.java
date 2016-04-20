package lt.virai.labanoroDraugai.ui.model;

/**
 * Created by Žilvinas on 2016-04-19.
 */
public class FbRegistrationInfo {
    private String clientId;
    private String redirectUri;
    private String code;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
