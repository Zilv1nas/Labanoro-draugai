package lt.virai.labanoroDraugai.ui.rest;

import lt.virai.labanoroDraugai.bl.services.UserService;
import lt.virai.labanoroDraugai.domain.entities.User;
import lt.virai.labanoroDraugai.domain.model.UserRole;
import lt.virai.labanoroDraugai.ui.security.Secured;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Žilvinas on 2016-03-08.
 */
@Stateless
@Path("/")
public class MainController {

    @Inject
    private UserService userService;

    //TODO remove
    @GET
    @Secured({UserRole.MEMBER})
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public User test() {
        return userService.get(1);
    }
}
