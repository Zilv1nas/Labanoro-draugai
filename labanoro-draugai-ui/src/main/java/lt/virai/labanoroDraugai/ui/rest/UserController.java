package lt.virai.labanoroDraugai.ui.rest;

import lt.virai.labanoroDraugai.bl.services.ClubSettingService;
import lt.virai.labanoroDraugai.bl.services.EmailService;
import lt.virai.labanoroDraugai.bl.services.TransactionService;
import lt.virai.labanoroDraugai.bl.services.UserService;
import lt.virai.labanoroDraugai.domain.model.UserRole;
import lt.virai.labanoroDraugai.ui.model.users.InvitationInfo;
import lt.virai.labanoroDraugai.ui.model.users.ProfileModel;
import lt.virai.labanoroDraugai.ui.security.RequiresPayment;
import lt.virai.labanoroDraugai.ui.security.Secured;
import lt.virai.labanoroDraugai.ui.validation.ValidationExceptionMapper;
import org.hibernate.validator.constraints.NotEmpty;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static lt.virai.labanoroDraugai.ui.validation.ValidationExceptionMapper.*;

/**
 * Created by Žilvinas on 2016-04-21.
 */
@Stateless
@Path("/user")
public class UserController {

    @Inject
    private UserService userService;
    @Inject
    private EmailService emailService;
    @Inject
    private ClubSettingService clubSettingService;
    @Inject
    private TransactionService transactionService;

    @RequiresPayment
    @Secured({UserRole.ADMIN})
    @POST
    @Path("/updateProfile")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateProfile(@Valid ProfileModel profileModel) {
        try {
            userService.updateUserProfile(profileModel.mapTo());
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @Secured
    @POST
    @Path("/updateCurrentUserProfile")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCurrentUserProfile(@Valid ProfileModel profileModel, @Context SecurityContext securityContext) {
        try {
            profileModel.setId(Integer.parseInt(securityContext.getUserPrincipal().getName()));
            userService.updateUserProfile(profileModel.mapTo());
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @RequiresPayment
    @Secured({UserRole.MEMBER, UserRole.ADMIN})
    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.ok().entity(userService.getAll()
                .stream().map(ProfileModel::new).collect(Collectors.toList())).build();
    }

    @RequiresPayment
    @Secured({UserRole.ADMIN})
    @POST
    @Path("/verify")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response verifyUser(int userId) {
        try {
            if (clubSettingService.isMemberCapacityExceeded()) {
                return Response.status(Response.Status.CONFLICT).entity("Maximum member capacity exceeded").build();
            }
            userService.verifyUser(userId);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @RequiresPayment
    @Secured({UserRole.MEMBER, UserRole.ADMIN})
    @POST
    @Path("/invite")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response invite(@Valid InvitationInfo invitationInfo) {
        try {
            emailService.sendInvitationEmail(invitationInfo.getToEmail(), invitationInfo.getFullName(), invitationInfo.getRedirectUrl());
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @Secured
    @GET
    @Path("/getProfile")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProfile(@Context SecurityContext securityContext) {
        try {
            Integer userId = Integer.parseInt(securityContext.getUserPrincipal().getName());
            ProfileModel profileModel = Optional.ofNullable(userService.get(userId))
                    .map(ProfileModel::new)
                    .orElseThrow(IllegalStateException::new);

            profileModel.setLastPaymentDate(transactionService.getLastAnnualPaymentDate(profileModel.getId()));
            profileModel.setAnnualPaymentSize(clubSettingService.getAnnualPaymentSize());
            return Response.ok().entity(profileModel).build();
        } catch (NumberFormatException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @RequiresPayment
    @Secured
    @GET
    @Path("/getMemberProfile/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMemberProfile(@PathParam("id") @NotNull Integer id) {
        try {
            ProfileModel profileModel = Optional.ofNullable(userService.get(id))
                    .map(ProfileModel::new)
                    .orElseThrow(IllegalStateException::new);
            return Response.ok().entity(profileModel).build();
        } catch (NumberFormatException | IllegalStateException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @RequiresPayment
    @Secured({UserRole.ADMIN})
    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") @NotNull Integer id) {
        try {
            userService.remove(id);
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.serverError().build();
        }
    }

    @Secured
    @DELETE
    @Path("/deleteCurrentUser")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCurrentUser(@Context SecurityContext securityContext) {
        try {
            userService.remove(Integer.parseInt(securityContext.getUserPrincipal().getName()));
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.serverError().build();
        }
    }

    @Secured
    @POST
    @Path("/askForRecommendations")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response askForRecommendations(@NotEmpty(message = "El. paštų sąrašas negali būti tuščias") Set<String> emails,
                                          @Context SecurityContext securityContext) {
        try {
            List<ValidationExceptionMapper.ValidationError> errors = new ArrayList<>();
            emails.forEach(e -> {
                if (!userService.emailExists(e)) {
                    errors.add(new ValidationExceptionMapper.ValidationError("recommendations", "Nerastas el. pašto adresas: " + e));
                }
            });

            if (!errors.isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST).entity(errors).build();
            }
            Integer userId = Integer.parseInt(securityContext.getUserPrincipal().getName());
            emailService.askForRecommendations(emails, userId);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }
}
