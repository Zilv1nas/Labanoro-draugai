package lt.virai.labanoroDraugai.ui.rest;

import lt.virai.labanoroDraugai.bl.services.ClubSettingService;
import lt.virai.labanoroDraugai.domain.model.UserRole;
import lt.virai.labanoroDraugai.ui.model.admin.ClubSettingsModel;
import lt.virai.labanoroDraugai.ui.security.RequiresPayment;
import lt.virai.labanoroDraugai.ui.security.Secured;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Žilvinas on 2016-05-19.
 */
@RequiresPayment
@Stateless
@Path("/settings")
public class SettingsController {

    @Inject
    private ClubSettingService clubSettingService;

    @Secured({UserRole.ADMIN})
    @GET
    @Path("/getAllSettings")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        try {
            ClubSettingsModel model = new ClubSettingsModel(clubSettingService.getAllSettings());
            return Response.ok(model).build();
        } catch (Exception ex) {
            return Response.serverError().build();
        }
    }

    @Secured({UserRole.ADMIN})
    @POST
    @Path("/updateSettings")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@Valid ClubSettingsModel clubSettingsModel) {
        try {
            clubSettingService.updateClubSettings(clubSettingsModel.mapTo());
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.serverError().build();
        }
    }
}
