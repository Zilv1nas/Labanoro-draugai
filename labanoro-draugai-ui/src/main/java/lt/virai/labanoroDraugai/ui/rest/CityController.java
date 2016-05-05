package lt.virai.labanoroDraugai.ui.rest;

import lt.virai.labanoroDraugai.domain.dao.CityDAO;
import lt.virai.labanoroDraugai.domain.entities.City;
import lt.virai.labanoroDraugai.domain.model.UserRole;
import lt.virai.labanoroDraugai.ui.model.residence.CityModel;
import lt.virai.labanoroDraugai.ui.security.Secured;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
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
    public Response save(@Valid CityModel cityModel) {
        try {
            if (cityModel.getId() != null && cityModel.getId() != 0) {
                throw new IllegalStateException("Cannot create city which has id already set.");
            }

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
    public Response update(@Valid CityModel cityModel) {
        try {
            if (cityModel.getId() == null || cityModel.getId() == 0)
                throw new IllegalStateException("Cannot update city when id is not set.");

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
    public Response get(@PathParam("id") @NotNull Integer id) {
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
    public Response delete(@PathParam("id") @NotNull Integer id) {
        try {
            cityDAO.remove(id);
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.serverError().build();
        }
    }
}
