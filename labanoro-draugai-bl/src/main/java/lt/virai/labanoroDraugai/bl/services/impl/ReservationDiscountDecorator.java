package lt.virai.labanoroDraugai.bl.services.impl;

import lt.virai.labanoroDraugai.bl.exceptions.LabanoroException;
import lt.virai.labanoroDraugai.bl.services.ReservationService;
import lt.virai.labanoroDraugai.domain.dao.ReservationDAO;
import lt.virai.labanoroDraugai.domain.dao.UserDAO;
import lt.virai.labanoroDraugai.domain.entities.Reservation;
import lt.virai.labanoroDraugai.domain.entities.User;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.Set;

/**
 * Created by Žilvinas on 2016-05-29.
 */
@Decorator
public class ReservationDiscountDecorator implements ReservationService {

    private static final int DISCOUNT_PERCENTAGE = 30;

    @Inject
    @Delegate
    private ReservationService reservationService;

    @Inject
    private ReservationDAO reservationDAO;

    @Inject
    private UserDAO userDAO;

    @Override
    public Reservation reserve(User user, Integer residenceId, LocalDate dateFrom, LocalDate dateTo, Set<Integer> extraServiceIds) throws LabanoroException {
        Reservation reservation = reservationService.reserve(user, residenceId, dateFrom, dateTo, extraServiceIds);

        int spent = reservation.getAmountSpent() * (100 - DISCOUNT_PERCENTAGE) / 100;
        int diff = reservation.getAmountSpent() - spent;
        reservation.setAmountSpent(spent);

        reservationDAO.update(reservation);
        user.setBalance(user.getBalance() + diff);
        userDAO.update(user);

        return reservation;
    }


    @Override
    public void cancel(Reservation reservation) {
        reservationService.cancel(reservation);
    }
}
