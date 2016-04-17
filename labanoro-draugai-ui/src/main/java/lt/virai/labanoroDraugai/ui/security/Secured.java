package lt.virai.labanoroDraugai.ui.security;

import lt.virai.labanoroDraugai.domain.model.UserRole;

import javax.ws.rs.NameBinding;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Žilvinas on 2016-04-16.
 */
@NameBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Secured {
    UserRole[] value() default {};
}
