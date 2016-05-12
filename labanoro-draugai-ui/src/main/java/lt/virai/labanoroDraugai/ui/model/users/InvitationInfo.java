package lt.virai.labanoroDraugai.ui.model.users;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import static lt.virai.labanoroDraugai.ui.util.ValidationUtils.EMAIL_REGEX;

/**
 * Created by Žilvinas on 2016-04-24.
 */
public class InvitationInfo {
    @NotEmpty
    private String fromName;
    @NotEmpty
    private String fromSurname;
    @Email(regexp = EMAIL_REGEX)
    private String toEmail;
    @NotEmpty
    private String redirectUrl;

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getFromSurname() {
        return fromSurname;
    }

    public void setFromSurname(String fromSurname) {
        this.fromSurname = fromSurname;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public String getFullName() {
        return fromName + " " + fromSurname;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}
