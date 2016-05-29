package lt.virai.labanoroDraugai.ui.rest;

import lt.virai.labanoroDraugai.bl.services.GroupService;
import lt.virai.labanoroDraugai.domain.dao.UserDAO;
import lt.virai.labanoroDraugai.domain.entities.User;
import lt.virai.labanoroDraugai.domain.model.UserRole;
import lt.virai.labanoroDraugai.ui.model.users.UserGroupModel;
import lt.virai.labanoroDraugai.ui.security.RequiresPayment;
import lt.virai.labanoroDraugai.ui.security.Secured;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

/**
 * Created by Mantas on 5/29/2016.
 */
@RequiresPayment
@Stateless
@Path("/groups")
public class GroupsController {

    @Inject
    private GroupService groupService;
    @Inject
    private UserDAO userDAO;

    @Secured({UserRole.ADMIN, UserRole.MEMBER})
    @GET
    @Path("/getUserPriority")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserPriority(@Context SecurityContext securityContext) {
        try {
            Integer userId = Integer.parseInt(securityContext.getUserPrincipal().getName());
            Integer priority = userDAO.get(userId).getUserGroup().getPriority();
            return Response.ok(priority).build();
        } catch (Exception ex) {
            return Response.serverError().build();
        }
    }

    @Secured({UserRole.ADMIN})
    @GET
    @Path("/getGroupSettings")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        try {
            UserGroupModel model = new UserGroupModel(groupService.getGroupSettings());
            return Response.ok(model).build();
        } catch (Exception ex) {
            return Response.serverError().build();
        }
    }

    @Secured({UserRole.ADMIN})
    @POST
    @Path("/updateGroups")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@Valid UserGroupModel userGroupModel) {
        try {
            groupService.updateGroups(userGroupModel.mapTo());
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.serverError().build();
        }
    }
}