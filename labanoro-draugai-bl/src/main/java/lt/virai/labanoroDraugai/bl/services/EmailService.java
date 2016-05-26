package lt.virai.labanoroDraugai.bl.services;

import com.sendgrid.SendGridException;

import javax.ejb.Asynchronous;
import java.util.List;

/**
 * Created by Žilvinas on 2016-04-24.
 */
public interface EmailService {
    void sendInvitationEmail(String email, String from, String redirectUrl) throws SendGridException;

    void askForRecommendations(List<String> emails, int userId) throws SendGridException;
}
