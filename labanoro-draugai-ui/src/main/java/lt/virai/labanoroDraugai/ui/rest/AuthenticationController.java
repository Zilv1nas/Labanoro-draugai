package lt.virai.labanoroDraugai.ui.rest;

import lt.virai.labanoroDraugai.bl.services.AuthService;
import lt.virai.labanoroDraugai.domain.entities.AuthenticationAttribute;
import lt.virai.labanoroDraugai.domain.entities.User;
import lt.virai.labanoroDraugai.domain.model.AuthAttributeEnum;
import lt.virai.labanoroDraugai.domain.model.UserRole;
import lt.virai.labanoroDraugai.ui.model.LoginInfo;
import lt.virai.labanoroDraugai.ui.model.RegistrationInfo;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Žilvinas on 2016-04-12.
 */
@Stateless
@Path("/")
public class AuthenticationController {
    @Inject
    private AuthService authService;

    @POST
    @Path("/login")
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
            user.setRole(UserRole.MEMBER);

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
}
