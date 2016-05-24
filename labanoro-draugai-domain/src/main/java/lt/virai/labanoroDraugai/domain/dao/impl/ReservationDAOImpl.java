package lt.virai.labanoroDraugai.domain.dao.impl;

import lt.virai.labanoroDraugai.domain.dao.AbstractDAO;
import lt.virai.labanoroDraugai.domain.dao.ReservationDAO;
import lt.virai.labanoroDraugai.domain.entities.Reservation;
import lt.virai.labanoroDraugai.domain.entities.Residence;
import lt.virai.labanoroDraugai.domain.entities.User;

import javax.ejb.Stateless;
import java.util.List;
import java.util.Objects;

/**
 * Created by Mantas on 4/30/2016.
 */
@Stateless
public class ReservationDAOImpl extends AbstractDAO<Reservation> implements ReservationDAO {
    @Override
    public List<Reservation> getReservationsForResidence(Residence residence) {
        Integer residenceId = residence.getId();

        return streams.streamAll(entityManager, Reservation.class)
                .where(u -> u.getResidence().getId().equals(residenceId)).toList();
    }

    @Override
    public List<Reservation> getReservationsForUser(User user) {
        Integer userId = user.getId();

        return streams.streamAll(entityManager, Reservation.class)
                .where(u -> u.getUser().getId().equals(userId)).toList();
    }
}
