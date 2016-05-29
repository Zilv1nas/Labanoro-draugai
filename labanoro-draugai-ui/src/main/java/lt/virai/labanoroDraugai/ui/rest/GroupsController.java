package lt.virai.labanoroDraugai.ui.rest;

import lt.virai.labanoroDraugai.bl.services.GroupService;
import lt.virai.labanoroDraugai.domain.model.UserRole;
import lt.virai.labanoroDraugai.ui.model.users.UserGroupModel;
import lt.virai.labanoroDraugai.ui.security.RequiresPayment;
import lt.virai.labanoroDraugai.ui.security.Secured;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Mantas on 5/29/2016.
 */
@RequiresPayment
@Stateless
@Path("/groups")
public class GroupsController {

    @Inject
    private GroupService groupService;

    @Secured({UserRole.ADMIN})
    @GET
    @Path("/getGroupSettings")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        try {
            UserGroupModel model = new UserGroupModel();
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
            groupService.updateClubSettings(userGroupModel.getGroupsNumber(), userGroupModel.getDaysInterval());
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.serverError().build();
        }
    }
}