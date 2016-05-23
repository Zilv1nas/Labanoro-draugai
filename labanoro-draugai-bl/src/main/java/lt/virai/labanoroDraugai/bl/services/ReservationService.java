package lt.virai.labanoroDraugai.bl.services;

import lt.virai.labanoroDraugai.bl.exceptions.LabanoroException;
import lt.virai.labanoroDraugai.domain.entities.ExtraService;
import lt.virai.labanoroDraugai.domain.entities.Reservation;
import lt.virai.labanoroDraugai.domain.entities.Residence;
import lt.virai.labanoroDraugai.domain.entities.User;

import java.time.LocalDate;
import java.util.Set;

/**
 * Created by Mantas on 5/17/2016.
 */
public interface ReservationService {
    void reserve(User user, Residence residence, LocalDate dateFrom, LocalDate dateTo, Set<ExtraService> extraServices) throws LabanoroException;
    void cancel(Reservation reservation);
}
