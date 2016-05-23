package lt.virai.labanoroDraugai.bl.interceptors;

import lt.virai.labanoroDraugai.bl.context.ThreadLocalContext;
import lt.virai.labanoroDraugai.bl.interceptors.binding.Logged;
import lt.virai.labanoroDraugai.domain.dao.UserDAO;
import lt.virai.labanoroDraugai.domain.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import java.lang.reflect.Method;

/**
 * Created by Žilvinas on 2016-05-21.
 */
@Logged
@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
public class TransactionsLogginInterceptor {
    private static final Logger LOG = LoggerFactory.getLogger(TransactionsLogginInterceptor.class);
    private static final String LOG_TEMPLATE = "Method: %s, Username: %s, Role: %s";

    @Context
    private SecurityContext securityContext;

    @Inject
    private UserDAO userDAO;

    @AroundInvoke
    public Object log(InvocationContext context) throws Exception {
        Method method = context.getMethod();
        String fullMethodName = method.getDeclaringClass().getSimpleName() + "." + method.getName();

        Integer userId = null;
        Object userIdObj = ThreadLocalContext.get("userid");
        if (userIdObj instanceof String) {
            userId = Integer.parseInt((String) userIdObj);
        }

        User user = userId != null ? userDAO.get(userId) : null;
        String username = user != null ? user.getEmail() : "UNKNOWN";
        String role = user != null ?  user.getRole().name() : "UNKNOWN";

        LOG.info(String.format(LOG_TEMPLATE, fullMethodName, username, role));
        return context.proceed();
    }
}
