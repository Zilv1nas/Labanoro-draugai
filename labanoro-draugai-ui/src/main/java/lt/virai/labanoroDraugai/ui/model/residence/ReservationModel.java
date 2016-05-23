package lt.virai.labanoroDraugai.ui.model.residence;

import lt.virai.labanoroDraugai.domain.entities.Reservation;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Set;

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

    @Valid
    private Set<ExtraServiceModel> extraServices;

    public ReservationModel() {
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

    public ReservationModel(Reservation reservation){

    }

    public Set<ExtraServiceModel> getExtraServices() {
        return extraServices;
    }

    public void setExtraServices(Set<ExtraServiceModel> extraServices) {
        this.extraServices = extraServices;
    }
}
