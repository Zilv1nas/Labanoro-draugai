package lt.virai.labanoroDraugai.ui.rest;

import lt.virai.labanoroDraugai.bl.exceptions.LabanoroException;
import lt.virai.labanoroDraugai.bl.services.ReservationService;
import lt.virai.labanoroDraugai.domain.dao.ReservationDAO;
import lt.virai.labanoroDraugai.domain.dao.ResidenceDAO;
import lt.virai.labanoroDraugai.domain.dao.UserDAO;
import lt.virai.labanoroDraugai.domain.entities.ExtraService;
import lt.virai.labanoroDraugai.domain.entities.Reservation;
import lt.virai.labanoroDraugai.domain.entities.Residence;
import lt.virai.labanoroDraugai.domain.entities.User;
import lt.virai.labanoroDraugai.domain.model.UserRole;
import lt.virai.labanoroDraugai.ui.model.residence.ExtraServiceModel;
import lt.virai.labanoroDraugai.ui.model.residence.ReservationHistoryModel;
import lt.virai.labanoroDraugai.ui.model.residence.ReservationModel;
import lt.virai.labanoroDraugai.ui.security.RequiresPayment;
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by Mantas on 5/19/2016.
 */
@RequiresPayment
@Stateless
@Path("/reservation")
public class ReservationController {
    @Inject
    private ReservationDAO reservationDAO;
    @Inject
    private ReservationService reservationService;
    @Inject
    private UserDAO userDAO;
    @Inject
    private ResidenceDAO residenceDAO;

    @Secured({UserRole.MEMBER, UserRole.ADMIN})
    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        try {
            List<ReservationHistoryModel> reservations = reservationDAO.getAll()
                    .stream().map(ReservationHistoryModel::new).collect(Collectors.toList());
            return Response.ok(reservations).build();
        } catch (Exception ex) {
            return Response.serverError().build();
        }
    }

    @Secured({UserRole.MEMBER, UserRole.ADMIN})
    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") @NotNull Integer id) {
        try {
            return Response.ok(new ReservationHistoryModel(reservationDAO.get(id))).build();
        } catch (Exception ex) {
            return Response.serverError().build();
        }
    }

    @Secured({UserRole.MEMBER, UserRole.ADMIN})
    @GET
    @Path("/getResidenceHistory/{residenceId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResidenceHistory(@PathParam("residenceId") @NotNull Integer residenceId) {
        try {
            Residence residence = residenceDAO.get(residenceId);
            List<ReservationHistoryModel> reservations = reservationDAO.getReservationsForResidence(residence)
                    .stream().map(ReservationHistoryModel::new).collect(Collectors.toList());
            return Response.ok(reservations).build();
        } catch (Exception ex) {
            return Response.serverError().build();
        }
    }

    @Secured({UserRole.MEMBER, UserRole.ADMIN})
    @GET
    @Path("/getUserHistory")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserHistory(@Context SecurityContext securityContext) {
        try {
            Integer userId = Integer.parseInt(securityContext.getUserPrincipal().getName());
            User user = userDAO.get(userId);
            List<ReservationHistoryModel> reservations = reservationDAO.getReservationsForUser(user)
                    .stream().map(ReservationHistoryModel::new).collect(Collectors.toList());
            return Response.ok(reservations).build();
        } catch (Exception ex) {
            return Response.serverError().build();
        }
    }

    @Secured({UserRole.MEMBER, UserRole.ADMIN})
    @POST
    @Path("/reserve")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response reserve(@Valid ReservationModel reservationModel,
                            @Context SecurityContext securityContext) throws LabanoroException {
        try {
            Integer userId = Integer.parseInt(securityContext.getUserPrincipal().getName());

            reservationService.reserve(userDAO.get(userId), reservationModel.getResidence().getId(),
                    reservationModel.getDuration().getDateFrom(), reservationModel.getDuration().getDateTo(),
                    reservationModel.getExtraServices().stream().map(ExtraServiceModel::getId).collect(Collectors.toSet()));

            return Response.ok().build();
        } catch (LabanoroException ex) {
            throw ex;
        } catch (Exception ex) {
            return Response.serverError().build();
        }
    }

    @Secured({UserRole.MEMBER, UserRole.ADMIN})
    @POST
    @Path("/cancel")
    @Produces(MediaType.APPLICATION_JSON)
    public Response cancel(@NotNull Integer id) {
        try {
            Reservation reservation = reservationDAO.get(id);
            if (reservation == null) {
                // TODO pritempt prie fronto (struktura).
                return Response.status(Response.Status.BAD_REQUEST).entity("Reservation does not exist").build();
            }
            reservationService.cancel(reservation);
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.serverError().build();
        }
    }
}
