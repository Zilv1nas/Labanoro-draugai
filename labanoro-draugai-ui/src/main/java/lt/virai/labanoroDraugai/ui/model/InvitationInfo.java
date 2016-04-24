package lt.virai.labanoroDraugai.ui.model;

/**
 * Created by Žilvinas on 2016-04-24.
 */
public class InvitationInfo {
    private String fromName;
    private String fromSurname;
    private String toEmail;

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
}
