package lt.virai.labanoroDraugai.domain.dao.impl;

import lt.virai.labanoroDraugai.domain.dao.AbstractDAO;
import lt.virai.labanoroDraugai.domain.dao.ReservationDAO;
import lt.virai.labanoroDraugai.domain.entities.Reservation;
import lt.virai.labanoroDraugai.domain.entities.Residence;

import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by Mantas on 4/30/2016.
 */
@Stateless
public class ReservationDAOImpl extends AbstractDAO<Reservation> implements ReservationDAO {
    @Override
    public List<Reservation> getReservationsForResidence(Residence residence) {
        return streams.streamAll(entityManager, Reservation.class)
                .where(u -> u.getResidence().getId() == residence.getId()).toList();
    }
}
