package lt.virai.labanoroDraugai.ui.rest;

import lt.virai.labanoroDraugai.bl.services.ResidenceService;
import lt.virai.labanoroDraugai.domain.dao.ResidenceDAO;
import lt.virai.labanoroDraugai.domain.entities.City;
import lt.virai.labanoroDraugai.domain.entities.Residence;
import lt.virai.labanoroDraugai.ui.model.CityModel;
import lt.virai.labanoroDraugai.ui.model.ModelState;
import lt.virai.labanoroDraugai.ui.model.ResidenceModel;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mantas on 4/20/2016.
 */
@Stateless
@Path("/residence")
public class ResidenceController {
    @Inject
    ResidenceService residenceService;
    @Inject
    ResidenceDAO residenceDAO;

    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(ResidenceModel residenceModel) {
        try {
            ModelState modelState = residenceModel.validate();
            if (residenceModel.getId() != null && residenceModel.getId() != 0)
                // todo property names hardcodinimas
                modelState.addError("id", "Cannot create residence which has id already set.");

            if (modelState.hasErrors())
                return modelState.buildBadResponse();

            Residence residence = residenceModel.mapTo();
            residenceService.create(residence);
            //todo fix pathing (war name and prefix 'rest' lost)
            return Response.created(UriBuilder.fromResource(ResidenceController.class).path("/get/{id}").build(residence.getId().toString())).entity(residence).build();
        } catch (Exception ex) {
            return Response.serverError().build();
        }
    }

    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(ResidenceModel residenceModel) {
        try {
            ModelState modelState = residenceModel.validate();
            if (residenceModel.getId() == null || residenceModel.getId() == 0)
                modelState.addError("id", "Cannot update residence when id is not set.");

            if (modelState.hasErrors())
                return modelState.buildBadResponse();

            Residence residence = residenceModel.mapTo();
            residenceDAO.update(residence);
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/getall")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        try {
            List<Residence> residences = residenceDAO.getAll();
            List<ResidenceModel> residencesModels = new ArrayList<ResidenceModel>();
            for (Residence residence : residences){
                ResidenceModel residenceModel = new ResidenceModel();
                residenceModel.mapFrom(residence);
                residencesModels.add(residenceModel);
            }
            return Response.ok(residencesModels).build();
        } catch (Exception ex) {
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id")Integer id) {
        try {
            Residence residence = residenceDAO.get(id);
            ResidenceModel residenceModel = new ResidenceModel();
            residenceModel.mapFrom(residence);
            return Response.ok(residenceModel).build();
        } catch (Exception ex) {
            return Response.serverError().build();
        }
    }

    @POST
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id")Integer id) {
        ModelState modelState = new ModelState();
        try {
            if(id == null){
                modelState.addError("id", "Id not specified");
                return modelState.buildBadResponse();
            }
            else {
                Residence residence = residenceDAO.get(id);
                if (residence == null)
                    modelState.addError("", "Residence does not exist.");
                if(modelState.hasErrors())
                    return modelState.buildBadResponse();
                residenceDAO.remove(residence);
                return Response.ok().build();
            }
        } catch (Exception ex) {
            return Response.serverError().build();
        }
    }
}
