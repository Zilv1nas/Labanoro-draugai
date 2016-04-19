package lt.virai.labanoroDraugai.ui.rest;

import lt.virai.labanoroDraugai.domain.dao.CityDAO;
import lt.virai.labanoroDraugai.domain.entities.City;
import lt.virai.labanoroDraugai.ui.model.CityModel;
import lt.virai.labanoroDraugai.ui.model.ModelState;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mantas on 4/19/2016.
 */
@Stateless
@Path("/city")
public class CityController {
    @Inject
    private CityDAO cityDAO;

    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(CityModel cityModel) {
        try {
            ModelState modelState = cityModel.validate();
            if (cityModel.getId() != 0 && cityModel.getId() != null)
                // todo property names hardcodinimas
                modelState.addError("id", "Cannot create city which has id already set.");

            if (modelState.hasErrors())
                return modelState.buildBadResponse();

            City city = cityModel.mapTo();
            cityDAO.save(city);
            return Response.created(UriBuilder.fromResource(CityController.class).path("/get/{id}").build(city.getId().toString())).entity(city).build();
        } catch (Exception ex) {
            return Response.serverError().build();
        }
    }

    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(CityModel cityModel) {
        try {
            ModelState modelState = cityModel.validate();
            if (cityModel.getId() == 0 || cityModel.getId() == null)
                modelState.addError("id", "Cannot update city when id is not set.");

            if (modelState.hasErrors())
                return modelState.buildBadResponse();

            City city = cityModel.mapTo();
            cityDAO.update(city);
            return Response.created(UriBuilder.fromResource(CityController.class).path("/get/{id}").build(city.getId().toString())).entity(city).build();
        } catch (Exception ex) {
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/getall")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        try {
            List<City> cities = cityDAO.getAll();
            List<CityModel> citiesModels = new ArrayList<CityModel>();
            for (City city : cities){
                CityModel cityModel = new CityModel();
                cityModel.mapFrom(city);
                citiesModels.add(cityModel);
            }
            return Response.ok(citiesModels).build();
        } catch (Exception ex) {
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id")Integer id) {
        try {
            City city = cityDAO.get(id);
            CityModel cityModel = new CityModel();
            cityModel.mapFrom(city);
            return Response.ok(cityModel).build();
        } catch (Exception ex) {
            return Response.serverError().build();
        }
    }

    @DELETE
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
                City city = cityDAO.get(id);
                if (city == null)
                    modelState.addError("", "City does not exist.");
                if(modelState.hasErrors())
                    return modelState.buildBadResponse();
                cityDAO.remove(city);
                return Response.ok(city).build();
            }
        } catch (Exception ex) {
            return Response.serverError().build();
        }
    }
}
