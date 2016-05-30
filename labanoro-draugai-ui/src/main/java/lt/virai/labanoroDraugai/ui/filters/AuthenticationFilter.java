package lt.virai.labanoroDraugai.ui.filters;

import lt.virai.labanoroDraugai.bl.context.ThreadLocalContext;
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
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

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
        if (token == null) {
            abort(requestContext);
            return;
        }
        Optional<String> userId = authService.getUserId(token);
        if (userId.isPresent()) {
            overrideSecurityFilter(requestContext, userId.get());
            ThreadLocalContext.put("userid", userId.get());
        } else {
            abort(requestContext);
        }
    }

    private void abort(ContainerRequestContext requestContext) {
        requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
    }

    private void overrideSecurityFilter(ContainerRequestContext requestContext, String userId) {
        requestContext.setSecurityContext(new SecurityContext() {

            @Override
            public Principal getUserPrincipal() {
                return () -> userId;
            }

            @Override
            public boolean isUserInRole(String role) {
                return true;
            }

            @Override
            public boolean isSecure() {
                return false;
            }

            @Override
            public String getAuthenticationScheme() {
                return null;
            }

        });
    }
}
