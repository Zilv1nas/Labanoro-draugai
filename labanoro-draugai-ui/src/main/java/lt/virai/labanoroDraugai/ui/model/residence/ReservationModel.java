package lt.virai.labanoroDraugai.ui.model.residence;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by Mantas on 4/30/2016.
 */
public class ReservationModel {
    private Integer id;
    @NotNull(message = "Residence is required.")
    private ResidenceModel residence;
    @NotNull(message = "Duration is required.")
    @Valid
    private WeekRangeModel duration;

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
