package lt.virai.labanoroDraugai.ui.rest;

import lt.virai.labanoroDraugai.bl.services.EmailService;
import lt.virai.labanoroDraugai.bl.services.UserService;
import lt.virai.labanoroDraugai.domain.model.UserRole;
import lt.virai.labanoroDraugai.ui.model.users.InvitationInfo;
import lt.virai.labanoroDraugai.ui.model.users.UserModel;
import lt.virai.labanoroDraugai.ui.security.Secured;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Žilvinas on 2016-04-21.
 */
@Stateless
@Path("/user")
public class UserController {

    @Inject
    private UserService userService;
    @Inject
    private EmailService emailService;

    @Secured
    @POST
    @Path("/updateProfile")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateProfile(@Valid UserModel userModel) {
        try {
            userService.updateUserProfile(userModel.mapTo());
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @Secured({UserRole.ADMIN})
    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.ok().entity(userService.getAll()
                .stream().map(UserModel::new).collect(Collectors.toList())).build();
    }

    @Secured({UserRole.ADMIN})
    @POST
    @Path("/verify")
    @Consumes(MediaType.APPLICATION_JSON)
    public void verifyUser(int userId) {
        userService.verifyUser(userId);
    }

    @Secured({UserRole.MEMBER, UserRole.ADMIN})
    @POST
    @Path("/invite")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response invite(@Valid InvitationInfo invitationInfo) {
        try {
            emailService.sendInvitationEmail(invitationInfo.getToEmail(), invitationInfo.getFullName());
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @Secured
    @GET
    @Path("/getProfile")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProfile(@Context SecurityContext securityContext) {
        try {
            Integer userId = Integer.parseInt(securityContext.getUserPrincipal().getName());
            UserModel userModel = Optional.ofNullable(userService.get(userId))
                    .map(UserModel::new)
                    .orElseThrow(IllegalStateException::new);

            return Response.ok().entity(userModel).build();
        } catch (NumberFormatException | IllegalStateException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
