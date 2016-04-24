package lt.virai.labanoroDraugai.ui.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lt.virai.labanoroDraugai.bl.services.AuthService;
import lt.virai.labanoroDraugai.domain.entities.AuthenticationAttribute;
import lt.virai.labanoroDraugai.domain.entities.User;
import lt.virai.labanoroDraugai.domain.model.AuthAttributeEnum;
import lt.virai.labanoroDraugai.ui.model.FbRegistrationInfo;
import lt.virai.labanoroDraugai.ui.model.LoginInfo;
import lt.virai.labanoroDraugai.ui.model.RegistrationInfo;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.FacebookApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.security.sasl.AuthenticationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Žilvinas on 2016-04-12.
 */
@Stateless
@Path("/")
public class AuthenticationController {

    private static final String FB_API_SECRET = "3f91d27120f281077033b389e1e81776"; //TODO add to properties

    private static final String FB_API_ID = "1538319626473322"; //TODO add to properties

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Inject
    private AuthService authService;

    @POST
    @Path("/loginWithFacebook")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginInfo loginInfo) {
        try {
            return Response.ok(authService.login(loginInfo.getUsername(), loginInfo.getPassword())).build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(RegistrationInfo registrationInfo) {
        try {
            User user = new User();
            user.setEmail(registrationInfo.getEmail());
            user.setName(registrationInfo.getName());
            user.setSurname(registrationInfo.getSurname());

            Set<AuthenticationAttribute> attrs = new HashSet<>();
            AuthenticationAttribute attr = new AuthenticationAttribute();
            attr.setName(AuthAttributeEnum.PASSWORD);
            attr.setValue(registrationInfo.getPassword());
            attrs.add(attr);
            user.setAuthenticationAttributes(attrs);

            return Response.ok(authService.register(user)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Path("/register/facebook")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticateWithFb(FbRegistrationInfo fbRegistrationInfo) {
        try {
            OAuthService fbService = new ServiceBuilder()
                    .apiKey(FB_API_ID)
                    .apiSecret(FB_API_SECRET)
                    .callback(fbRegistrationInfo.getRedirectUri())
                    .provider(FacebookApi.class)
                    .build();

            Token token = getAccessToken(fbService, fbRegistrationInfo.getCode());

            String response = getResponseForProfile(fbService, token).getBody();
            String email = resolveFbField("email", response);
            String faceBookId = resolveFbField("id", response);

            if (authService.isAlreadyRegistered(email)) {
                return loginWithFb(faceBookId);
            } else {
                return registerWithFb(email, faceBookId, response);
            }
        } catch (Exception e) {
           return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    private Response registerWithFb(String email, String facebookId, String response) throws IOException {
        User user = new User();
        user.setEmail(email);
        user.setName(resolveFbField("first_name", response));
        user.setSurname(resolveFbField("last_name", response));

        AuthenticationAttribute attr = new AuthenticationAttribute();
        attr.setName(AuthAttributeEnum.FACEBOOK_ID);
        attr.setValue(facebookId);
        user.getAuthenticationAttributes().add(attr);

        return Response.ok().entity(authService.registerFacebookUser(user)).build();
    }

    private Response loginWithFb(String facebookId) throws AuthenticationException {
            return Response.ok().entity(authService.loginWithFacebook(facebookId)).build();
    }

    private String resolveFbField(String field, String response) throws IOException {
        JsonNode node = objectMapper.readTree(response);
        return node.get(field).asText();
    }

    private Token getAccessToken(OAuthService fbService, String code) {
        Verifier verifier = new Verifier(code);
        return fbService.getAccessToken(Token.empty(), verifier);
    }

    private org.scribe.model.Response getResponseForProfile(OAuthService fbService, Token accessToken) {
        OAuthRequest oauthRequest = new OAuthRequest(Verb.GET, "https://graph.facebook.com/v2.5/me?fields=id,first_name,last_name,email");
        fbService.signRequest(accessToken, oauthRequest);
        return oauthRequest.send();
    }
}
