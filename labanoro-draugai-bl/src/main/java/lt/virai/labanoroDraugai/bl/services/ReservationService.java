package lt.virai.labanoroDraugai.bl.services;

import lt.virai.labanoroDraugai.bl.exceptions.LabanoroException;
import lt.virai.labanoroDraugai.domain.entities.Reservation;
import lt.virai.labanoroDraugai.domain.entities.User;

import java.time.LocalDate;
import java.util.Set;

/**
 * Created by Mantas on 5/17/2016.
 */
public interface ReservationService {

    Reservation reserve(User user, Integer residenceId, LocalDate dateFrom, LocalDate dateTo, Set<Integer> extraServiceIds) throws LabanoroException;

    void cancel(Reservation reservation);
}
