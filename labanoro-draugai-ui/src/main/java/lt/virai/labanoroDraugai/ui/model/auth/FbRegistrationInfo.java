package lt.virai.labanoroDraugai.ui.model.auth;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by Žilvinas on 2016-04-19.
 */
public class FbRegistrationInfo {
    @NotEmpty
    private String clientId;
    private String redirectUri;
    @NotEmpty
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
