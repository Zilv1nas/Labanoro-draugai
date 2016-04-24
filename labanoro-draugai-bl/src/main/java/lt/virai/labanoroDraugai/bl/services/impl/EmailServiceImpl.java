package lt.virai.labanoroDraugai.bl.services.impl;

import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;
import lt.virai.labanoroDraugai.bl.services.EmailService;

import javax.ejb.Stateless;
import java.util.Objects;

/**
 * Created by Žilvinas on 2016-04-24.
 */
@Stateless
public class EmailServiceImpl implements EmailService {

    private static final String SEND_GRID_API_KEY = "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"; //TODO add to properties

    private static final SendGrid sendgrid = new SendGrid(SEND_GRID_API_KEY);

    //TODO read email template from propeties
    @Override
    public void sendInvitationEmail(String email, String from) throws SendGridException {
        Objects.requireNonNull(email);

        SendGrid.Email newEmail = new SendGrid.Email();
        newEmail.addTo(email);
        newEmail.setFrom("pakvietimas@labanoro-draugai.lt");
        newEmail.setSubject("Pakvietimas į Labanoro Draugų klubą");

        //TODO get forward url as a parameter
        String message = String.format("Sveiki, %s jus pakvietė į Labanoro Draugų klubą.\n " +
                "Norėdami užpildyti stojimo anketą apsilankykite šiame puslapyje: %s", from, "http://localhost:8080/labanoro-draugai/login");

        newEmail.setHtml(message);

        sendgrid.send(newEmail);
    }
}
