package lt.virai.labanoroDraugai.ui.rest;

import lt.virai.labanoroDraugai.domain.dao.CityDAO;
import lt.virai.labanoroDraugai.domain.entities.City;
import lt.virai.labanoroDraugai.domain.model.UserRole;
import lt.virai.labanoroDraugai.ui.model.CityModel;
import lt.virai.labanoroDraugai.ui.model.ModelState;
import lt.virai.labanoroDraugai.ui.security.Secured;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Mantas on 4/19/2016.
 */
@Stateless
@Path("/city")
public class CityController {
    @Inject
    private CityDAO cityDAO;

    @Secured({UserRole.ADMIN})
    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(CityModel cityModel) {
        try {
            ModelState modelState = cityModel.validate();
            if (cityModel.getId() != null && cityModel.getId() != 0)
                // todo property names hardcodinimas
                modelState.addError("id", "Cannot create city which has id already set.");

            if (modelState.hasErrors())
                return modelState.buildBadResponse();

            City city = cityModel.mapTo();
            cityDAO.save(city);
            //todo fix pathing (war name and prefix 'rest' lost)
            return Response.created(UriBuilder.fromResource(CityController.class).path("/get/{id}").build(city.getId().toString())).entity(city).build();
        } catch (Exception ex) {
            return Response.serverError().build();
        }
    }

    @Secured({UserRole.ADMIN})
    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(CityModel cityModel) {
        try {
            ModelState modelState = cityModel.validate();
            if (cityModel.getId() == null || cityModel.getId() == 0)
                modelState.addError("id", "Cannot update city when id is not set.");

            if (modelState.hasErrors())
                return modelState.buildBadResponse();

            City city = cityModel.mapTo();
            cityDAO.update(city);
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.serverError().build();
        }
    }

    @Secured
    @GET
    @Path("/getall")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        try {
            List<CityModel> citiesModels = cityDAO.getAll()
                    .stream().map(CityModel::new).collect(Collectors.toList());

            return Response.ok(citiesModels).build();
        } catch (Exception ex) {
            return Response.serverError().build();
        }
    }

    @Secured
    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") Integer id) {
        try {
            return Response.ok(new CityModel(cityDAO.get(id))).build();
        } catch (Exception ex) {
            return Response.serverError().build();
        }
    }

    @Secured({UserRole.ADMIN})
    @POST
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Integer id) {
        ModelState modelState = new ModelState();
        try {
            if (id == null) {
                modelState.addError("id", "Id not specified");
                return modelState.buildBadResponse();
            } else {
                City city = cityDAO.get(id);
                if (city == null)
                    modelState.addError("", "City does not exist.");
                if (modelState.hasErrors())
                    return modelState.buildBadResponse();
                cityDAO.remove(city);
                return Response.ok().build();
            }
        } catch (Exception ex) {
            return Response.serverError().build();
        }
    }
}
