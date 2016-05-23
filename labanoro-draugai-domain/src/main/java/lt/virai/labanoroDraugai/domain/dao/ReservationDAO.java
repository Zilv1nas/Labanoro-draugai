package lt.virai.labanoroDraugai.domain.dao;

import lt.virai.labanoroDraugai.domain.entities.Reservation;
import lt.virai.labanoroDraugai.domain.entities.Residence;

import java.util.List;

/**
 * Created by Mantas on 4/30/2016.
 */
public interface ReservationDAO extends DAO<Reservation> {
    List<Reservation> getReservationsForResidence(Residence residence);
}
