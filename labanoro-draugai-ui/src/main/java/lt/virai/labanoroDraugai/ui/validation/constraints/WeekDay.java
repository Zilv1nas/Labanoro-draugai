package lt.virai.labanoroDraugai.ui.validation.constraints;

import lt.virai.labanoroDraugai.ui.validation.validators.WeekDayValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Size;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.time.DayOfWeek;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Žilvinas on 2016-05-05.
 */
@Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = WeekDayValidator.class)
@Documented
public @interface WeekDay {
    String message() default "Wrong day of the week.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    DayOfWeek value() default DayOfWeek.MONDAY;

    @Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        Size[] value();
    }
}