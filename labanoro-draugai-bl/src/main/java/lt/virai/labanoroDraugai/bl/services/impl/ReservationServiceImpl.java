package lt.virai.labanoroDraugai.bl.services.impl;

import lt.virai.labanoroDraugai.bl.exceptions.LabanoroException;
import lt.virai.labanoroDraugai.bl.interceptors.binding.Logged;
import lt.virai.labanoroDraugai.bl.services.ReservationService;
import lt.virai.labanoroDraugai.domain.dao.ReservationDAO;
import lt.virai.labanoroDraugai.domain.dao.ResidenceDAO;
import lt.virai.labanoroDraugai.domain.dao.UserDAO;
import lt.virai.labanoroDraugai.domain.entities.ExtraService;
import lt.virai.labanoroDraugai.domain.entities.Reservation;
import lt.virai.labanoroDraugai.domain.entities.Residence;
import lt.virai.labanoroDraugai.domain.entities.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.Math.toIntExact;

/**
 * Created by Mantas on 5/17/2016.
 */

@Stateless
public class ReservationServiceImpl implements ReservationService {

    @Inject
    private ReservationDAO reservationDAO;

    @Inject
    private UserDAO userDAO;

    @Inject
    private ResidenceDAO residenceDAO;

    @Logged
    @Override
    public Reservation reserve(User user, Integer residenceId, LocalDate dateFrom, LocalDate dateTo, Set<Integer> extraServiceIds) throws LabanoroException {
        Objects.requireNonNull(user);
        Objects.requireNonNull(residenceId);
        Objects.requireNonNull(dateFrom);
        Objects.requireNonNull(dateTo);
        Residence residence = residenceDAO.get(residenceId);
        if (dateFrom.getDayOfWeek() != DayOfWeek.MONDAY) {
            throw new LabanoroException("Date from has to be on monday.");
        }
        if (dateTo.getDayOfWeek() != DayOfWeek.SUNDAY) {
            throw new LabanoroException("Date to has to be on sunday.");
        }
        if (dateFrom.isBefore(residence.getAvailableFrom())) {
            throw new LabanoroException("Date from is less than residence is available from.");
        }
        if (dateTo.isAfter(residence.getAvailableUntil())) {
            throw new LabanoroException("Date to is more than residence is available until.");
        }
        if (dateTo.isBefore(dateFrom)) {
            throw new LabanoroException("Date to cannot be less than date from");
        }

        List<Reservation> reservationsForResidence = reservationDAO.getReservationsForResidence(residence);
        for (Reservation existingReservation : reservationsForResidence) {
            // Per du if'us, kad skaitomiau butu.
            if ((dateFrom.isAfter(existingReservation.getDateFrom()) && dateFrom.isBefore(existingReservation.getDateTo()))
                    || dateFrom.atStartOfDay().equals(existingReservation.getDateFrom().atStartOfDay())) {
                throw new LabanoroException("There already are reservations for this period.");
            }
            if ((dateTo.isBefore(existingReservation.getDateTo()) && dateTo.isAfter(existingReservation.getDateFrom()))
                    || dateTo.atStartOfDay().equals(existingReservation.getDateTo().atStartOfDay())) {
                throw new LabanoroException("There already are reservations for this period.");
            }
        }
        //TODO check if user has paid yearly fee.

        //Note: is akies primetu, kad gerai skaiciuot turetu
        Integer totalWeeks = toIntExact(Duration.between(dateFrom.atTime(0, 0), dateTo.atTime(0, 0)).toDays() / 7) + 1;
        Integer amountToSpend = totalWeeks * residence.getWeeklyPrice();

        Set<ExtraService> extraServices = new HashSet<>();
        if (extraServiceIds != null) {
            extraServices = residence.getExtraServices().stream()
                    .filter(es -> extraServiceIds.contains(es.getId()))
                    .collect(Collectors.toSet());

            amountToSpend += extraServices.stream()
                    .map(ExtraService::getPrice)
                    .reduce((e1, e2) -> e1 + e2)
                    .orElse(0);
        }

        if (user.getBalance() < amountToSpend) {
            throw new LabanoroException("Balance is too low.");
        }

        Reservation reservation = new Reservation(user, residence, dateFrom, dateTo);
        reservation.setAmountSpent(amountToSpend);
        reservation.setSelectedExtraServices(extraServices);

        user.setBalance(user.getBalance() - amountToSpend);

        reservationDAO.save(reservation);
        userDAO.update(user);

        return reservation;
    }

    @Override
    public void cancel(Reservation reservation) {
        Objects.requireNonNull(reservation);

        if (LocalDate.now().isAfter(reservation.getDateFrom())) {
            Integer weeksPassedAlready = toIntExact(Duration.between(LocalDate.now().with(DayOfWeek.MONDAY).atTime(0, 0),
                    reservation.getDateTo().atTime(0, 0)).toDays() / 7) + 1;
            Integer totalWeeks = toIntExact(Duration.between(reservation.getDateFrom().atTime(0, 0), reservation.getDateTo().atTime(0, 0)).toDays() / 7) + 1;

            if (Objects.equals(totalWeeks, weeksPassedAlready)) {
                fullyRefund(reservation);
            } else {
                Integer amountToRefund = reservation.getAmountSpent() * weeksPassedAlready / totalWeeks;

                User user = reservation.getUser();
                user.setBalance(user.getBalance() + amountToRefund);

                reservation.setAmountSpent(reservation.getAmountSpent() - amountToRefund);
                reservation.setDateTo(LocalDate.now().with(DayOfWeek.SUNDAY));

                userDAO.update(user);
                reservationDAO.update(reservation);
            }
        } else {
            fullyRefund(reservation);
        }
    }

    private void fullyRefund(Reservation reservation) {
        User user = reservation.getUser();
        user.setBalance(user.getBalance() + reservation.getAmountSpent());

        userDAO.update(user);
        reservationDAO.remove(reservation);
    }
}
