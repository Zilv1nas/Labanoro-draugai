package lt.virai.labanoroDraugai.ui.filters;

import lt.virai.labanoroDraugai.bl.services.AuthService;
import lt.virai.labanoroDraugai.ui.security.Secured;
import lt.virai.labanoroDraugai.ui.util.AuthUtils;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Created by Žilvinas on 2016-04-16.
 */

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    @Inject
    private AuthService authService;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String token = AuthUtils.extractToken(requestContext.getHeaderString(HttpHeaders.AUTHORIZATION));

        if (token == null || !authService.isAuthorized(token)) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
}
