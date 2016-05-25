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
    @NotNull(message = "Data nuo yra privaloma")
    @WeekDay(value = DayOfWeek.MONDAY, message = "\"Data nuo\" privalo būti pirmadienis")
    private LocalDate dateFrom;

    @NotNull(message = "Data iki yra privaloma")
    @WeekDay(value = DayOfWeek.SUNDAY, message = "\"Data nuo\" privalo būti sekmadienis")
    private LocalDate dateTo;

    public WeekRangeModel () {}

    public WeekRangeModel (LocalDate from, LocalDate to){
        this.dateFrom = from;
        this.dateTo = to;
    }

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
