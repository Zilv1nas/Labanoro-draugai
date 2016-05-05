package lt.virai.labanoroDraugai.ui.validation.validators;

import lt.virai.labanoroDraugai.ui.validation.constraints.WeekDay;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

/**
 * Created by Žilvinas on 2016-05-05.
 */
public class WeekDayValidator implements ConstraintValidator<WeekDay, LocalDate> {

    private java.time.DayOfWeek dayOfWeek;

    public void initialize(WeekDay constraint) {
        this.dayOfWeek = constraint.value();
    }

    public boolean isValid(LocalDate obj, ConstraintValidatorContext context) {
        return dayOfWeek == obj.getDayOfWeek();
    }
}
