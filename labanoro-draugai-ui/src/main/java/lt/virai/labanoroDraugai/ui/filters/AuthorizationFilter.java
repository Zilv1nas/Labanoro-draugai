package lt.virai.labanoroDraugai.ui.filters;

import lt.virai.labanoroDraugai.bl.services.AuthService;
import lt.virai.labanoroDraugai.domain.model.UserRole;
import lt.virai.labanoroDraugai.ui.security.Secured;
import lt.virai.labanoroDraugai.ui.util.AuthUtils;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Žilvinas on 2016-04-16.
 */
@Secured
@Provider
@Priority(Priorities.AUTHORIZATION)
public class AuthorizationFilter implements ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;

    @Inject
    private AuthService authService;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String token = AuthUtils.extractToken(requestContext.getHeaderString(HttpHeaders.AUTHORIZATION));

        List<UserRole> classRoles = getRoles(resourceInfo.getResourceClass());
        List<UserRole> methodRoles = getRoles(resourceInfo.getResourceMethod());

        if (token == null || !checkRoles(token, methodRoles.isEmpty() ? classRoles : methodRoles)) {
            requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).build());
        }
    }

    private boolean checkRoles(String token, List<UserRole> userRoles) {
        return authService.hasRoles(token, userRoles);
    }

    private List<UserRole> getRoles(AnnotatedElement annotatedElement) {
        if (annotatedElement == null) {
            return Collections.emptyList();
        }

        Secured secured = annotatedElement.getAnnotation(Secured.class);
        return secured == null ? Collections.emptyList() : Arrays.asList(secured.value());
    }
}
