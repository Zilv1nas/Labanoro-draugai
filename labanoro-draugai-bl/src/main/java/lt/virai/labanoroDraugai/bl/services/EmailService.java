package lt.virai.labanoroDraugai.bl.services;

import com.sendgrid.SendGridException;

import java.util.Set;

/**
 * Created by Žilvinas on 2016-04-24.
 */
public interface EmailService {
    void sendInvitationEmail(String email, String from, String redirectUrl) throws SendGridException;

    void askForRecommendations(Set<String> emails, int userId) throws SendGridException;
}
