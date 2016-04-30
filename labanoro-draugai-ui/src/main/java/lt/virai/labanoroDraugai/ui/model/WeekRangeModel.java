package lt.virai.labanoroDraugai.ui.model;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * Created by Mantas on 4/30/2016.
 */
public class WeekRangeModel implements ValidatableModel {
    private LocalDate dateFrom;
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

    @Override
    public ModelState validate() {
        ModelState modelState = new ModelState();
        if(dateFrom == null)
            modelState.addError("dateFrom", "Date from is required.");
        else if(dateFrom.getDayOfWeek() != DayOfWeek.MONDAY)
            modelState.addError("dateFrom", "Date from always has to be monday.");
        if(dateTo != null && dateTo.getDayOfWeek() != DayOfWeek.SUNDAY)
            modelState.addError("dateTo", "Date to always has to be sunday.");

        return modelState;
    }
}
