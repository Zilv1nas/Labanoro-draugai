package lt.virai.labanoroDraugai.ui.rest;

import lt.virai.labanoroDraugai.bl.exceptions.LabanoroException;
import lt.virai.labanoroDraugai.bl.services.TransactionService;
import lt.virai.labanoroDraugai.domain.model.UserRole;
import lt.virai.labanoroDraugai.ui.model.purchases.PointPurchaseModel;
import lt.virai.labanoroDraugai.ui.security.Secured;
import org.hibernate.validator.constraints.Range;

import javax.ejb.Stateless;
import javax.inject.Inject;
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
import java.util.stream.Collectors;

/**
 * Created by Žilvinas on 2016-05-10.
 */
@Stateless
@Path("/purchases")
public class TransactionController {
    @Inject
    private TransactionService transactionService;

    @Secured({UserRole.MEMBER, UserRole.ADMIN})
    @POST
    @Path("/createPurchase")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPurchase(@NotNull(message = "Point amount is required") @Range(min = 1) Integer amount,
                                   @Context SecurityContext securityContext) {
        try {
            Integer userId = Integer.parseInt(securityContext.getUserPrincipal().getName());
            transactionService.createPurchase(userId, amount);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @Secured({UserRole.ADMIN})
    @GET
    @Path("/getAllPurchases")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPurchases() {
        try {
            List<PointPurchaseModel> purchases = transactionService.getAllPurchases().stream()
                    .map(PointPurchaseModel::new).collect(Collectors.toList());
            return Response.ok(purchases).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @Secured({UserRole.ADMIN})
    @POST
    @Path("/confirmPurchase")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response confirmPurchase(@NotNull Integer purchaseId) {
        try {
            transactionService.confirmPurchase(purchaseId);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Secured({UserRole.ADMIN})
    @POST
    @Path("/rejectPurchase")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response rejectPurchase(@NotNull Integer purchaseId) {
        try {
            transactionService.rejectPurchase(purchaseId);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Secured
    @GET
    @Path("/payAnnualPayment")
    public Response payAnnualPayment(@Context SecurityContext securityContext) {
        try {
            Integer userId = Integer.parseInt(securityContext.getUserPrincipal().getName());
            if (transactionService.hasUserPaidAnnualPayment(userId)) {
                throw new LabanoroException("Nario mokestis jau yra sumokėtas!");
            }
            transactionService.payAnnualPayment(userId);
            return Response.ok().build();
        } catch (LabanoroException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @Secured({UserRole.ADMIN})
    @POST
    @Path("sendPoints/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response sendPoints(@PathParam("id") @NotNull Integer id, @NotNull @Range(min = 0) Integer amount) {
        try {
            transactionService.sendPointsToUser(id, amount);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }
}
