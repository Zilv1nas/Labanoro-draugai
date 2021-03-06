package lt.virai.labanoroDraugai.bl.exceptions;

/**
 * Created by Mantas on 5/19/2016.
 */
public class LabanoroException extends Exception {
    private static final long serialVersionUID = -2941827297136873420L;

    private String key = "";

    public LabanoroException() {
    }

    public LabanoroException(String key, String message) {
        super(message);
        this.key = key;
    }

    public LabanoroException(String message) {
        super(message);
    }

    public LabanoroException(String message, Throwable cause) {
        super(message, cause);
    }

    public LabanoroException(Throwable cause) {
        super(cause);
    }

    public String getKey() {
        return key;
    }
}
