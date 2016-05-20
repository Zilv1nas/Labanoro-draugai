package lt.virai.labanoroDraugai.ui.filters;

import lt.virai.labanoroDraugai.bl.services.TransactionService;
import lt.virai.labanoroDraugai.ui.security.RequiresPayment;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Created by Žilvinas on 2016-05-19.
 */
@RequiresPayment
@Provider
@Priority(Priorities.USER)
public class AnnualPaymentFilter implements ContainerRequestFilter {
    @Inject
    private TransactionService transactionService;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        int userId = Integer.parseInt(requestContext.getSecurityContext().getUserPrincipal().getName());
        if (!transactionService.hasUserPaidAnnualPayment(userId)) {
            requestContext.abortWith(Response.status(Response.Status.PAYMENT_REQUIRED).build());
        }
    }
}
