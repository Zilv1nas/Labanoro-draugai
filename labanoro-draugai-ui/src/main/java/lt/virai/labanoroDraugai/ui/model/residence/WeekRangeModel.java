package lt.virai.labanoroDraugai.ui.model.residence;

import lt.virai.labanoroDraugai.ui.validation.constraints.WeekDay;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * Created by Mantas on 4/30/2016.
 */
public class WeekRangeModel {
    @NotNull(message = "Date from is required.")
    @WeekDay(value = DayOfWeek.MONDAY, message = "Date from always has to be monday.")
    private LocalDate dateFrom;

    @NotNull(message = "Date to is required.")
    @WeekDay(value = DayOfWeek.SUNDAY, message = "Date from always has to be sunday.")
    private LocalDate dateTo;

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }
}
