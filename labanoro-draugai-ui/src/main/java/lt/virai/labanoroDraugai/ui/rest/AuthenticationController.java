package lt.virai.labanoroDraugai.ui.rest;

import lt.virai.labanoroDraugai.bl.services.AuthService;
import lt.virai.labanoroDraugai.ui.model.LoginInfo;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginInfo loginInfo) {
        try {
            return Response.ok(authService.login(loginInfo.getUsername(), loginInfo.getPassword())).build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }


}
