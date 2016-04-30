package lt.virai.labanoroDraugai.ui.model;

import lt.virai.labanoroDraugai.domain.entities.Reservation;

import javax.enterprise.inject.Model;

/**
 * Created by Mantas on 4/30/2016.
 */
public class ReservationModel implements ValidatableModel {
    private Integer id;
    private ResidenceModel residence;
    private WeekRangeModel duration;

    @Override
    public ModelState validate() {
        ModelState modelState = new ModelState();
        if(residence == null)
            modelState.addError("residence", "Residence is required.");
        if(duration == null)
            modelState.addError("duration", "Duration is required.");
        else
            modelState.merge(duration.validate(), "duration");

        return modelState;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ResidenceModel getResidence() {
        return residence;
    }

    public void setResidence(ResidenceModel residence) {
        this.residence = residence;
    }

    public void setDuration(WeekRangeModel duration) {
        this.duration = duration;
    }

    public WeekRangeModel getDuration() {
        return duration;
    }
}
