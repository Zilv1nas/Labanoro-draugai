package lt.virai.labanoroDraugai.ui.rest;

import lt.virai.labanoroDraugai.bl.services.ResidenceService;
import lt.virai.labanoroDraugai.domain.dao.ResidenceDAO;
import lt.virai.labanoroDraugai.domain.entities.Residence;
import lt.virai.labanoroDraugai.domain.model.UserRole;
import lt.virai.labanoroDraugai.domain.model.search.ResidenceSearchRequest;
import lt.virai.labanoroDraugai.ui.model.residence.ResidenceModel;
import lt.virai.labanoroDraugai.ui.security.RequiresPayment;
import lt.virai.labanoroDraugai.ui.security.Secured;

import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
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
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Mantas on 4/20/2016.
 */
@RequiresPayment
@Stateless
@Path("/residence")
public class ResidenceController {
    @Inject
    private ResidenceService residenceService;

    @Secured({UserRole.ADMIN})
    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(@Valid ResidenceModel residenceModel) {
        try {
            if (residenceModel.getId() != null && residenceModel.getId() != 0)
                throw new IllegalStateException("Cannot create residence which has id already set.");

            return Response.ok(residenceService.create(residenceModel.mapTo())).build();
        } catch (Exception ex) {
            return Response.serverError().build();
        }
    }

    @Secured({UserRole.ADMIN})
    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@Valid ResidenceModel residenceModel) {
        try {
            if (residenceModel.getId() == null || residenceModel.getId() == 0)
                throw new IllegalStateException("Cannot update residence when id is not set.");

            residenceService.update(residenceModel.mapTo());
            return Response.ok().build();
        } catch (EJBTransactionRolledbackException e) {
            while (e.getCause() != null) {
                if (e.getCause() instanceof OptimisticLockException) {
                    return Response.status(Response.Status.CONFLICT).build();
                }
            }
            throw e;
        } catch (Exception ex) {
            return Response.serverError().build();
        }
    }

    @Secured
    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        try {
            List<ResidenceModel> residences = residenceService.getAll()
                    .stream().map(ResidenceModel::new).collect(Collectors.toList());
            return Response.ok(residences).build();
        } catch (Exception ex) {
            return Response.serverError().build();
        }
    }

    @Secured
    @POST
    @Path("/searchResidences")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchResidences(@NotNull ResidenceSearchRequest residenceSearchRequest) {
        try {
            return Response.ok(residenceService.searchResidences(residenceSearchRequest)).build();
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
            Residence residence = residenceService.get(id);
            ResidenceModel residenceModel = new ResidenceModel(residence);
            return Response.ok(residenceModel).build();
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
            residenceService.remove(id);
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.serverError().build();
        }
    }
}
