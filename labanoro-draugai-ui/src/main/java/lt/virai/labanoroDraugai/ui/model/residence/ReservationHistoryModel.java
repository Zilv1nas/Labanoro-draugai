package lt.virai.labanoroDraugai.ui.model.residence;

import lt.virai.labanoroDraugai.domain.entities.Reservation;
import lt.virai.labanoroDraugai.ui.model.users.ShortUserModel;

/**
 * Created by Mantas on 4/30/2016.
 */
public class ReservationHistoryModel {
    private Integer id;
    private Integer price;
    private ResidenceModel residence;
    private ShortUserModel user;
    private WeekRangeModel duration;

    public ReservationHistoryModel() {
    }

    public ReservationHistoryModel(Reservation reservation) {
        id = reservation.getId();
        price = reservation.getAmountSpent();
        residence = new ResidenceModel(reservation.getResidence());
        user = new ShortUserModel(reservation.getUser());
        duration = new WeekRangeModel();
        duration.setDateFrom(reservation.getDateFrom());
        duration.setDateTo(reservation.getDateTo());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public ResidenceModel getResidence() {
        return residence;
    }

    public void setResidence(ResidenceModel residence) {
        this.residence = residence;
    }

    public ShortUserModel getUser() {
        return user;
    }

    public void setUser(ShortUserModel user) {
        this.user = user;
    }

    public WeekRangeModel getDuration() {
        return duration;
    }

    public void setDuration(WeekRangeModel duration) {
        this.duration = duration;
    }
}
