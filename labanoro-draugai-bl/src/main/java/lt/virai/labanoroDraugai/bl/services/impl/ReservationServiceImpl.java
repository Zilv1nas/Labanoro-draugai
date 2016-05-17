package lt.virai.labanoroDraugai.bl.services.impl;

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
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

import static java.lang.Math.toIntExact;

/**
 * Created by Mantas on 5/17/2016.
 */

@Stateless
public class ReservationServiceImpl implements ReservationService{

    @Inject
    ReservationDAO reservationDAO;

    @Inject
    UserDAO userDAO;

    @Override
    public void reserve(User user, Residence residence, LocalDate dateFrom, LocalDate dateTo, Set<ExtraService> extraServices) {
        Objects.requireNonNull(user);
        Objects.requireNonNull(residence);
        Objects.requireNonNull(dateFrom);
        Objects.requireNonNull(dateTo);
        if(dateFrom.getDayOfWeek() != DayOfWeek.MONDAY){
            throw new IllegalArgumentException("Date from has to be on monday.");
        }
        if(dateTo.getDayOfWeek() != DayOfWeek.SUNDAY){
            throw new IllegalArgumentException("Date to has to be on sunday.");
        }
        if(dateFrom.isBefore(residence.getAvailableFrom())){
            throw new IllegalArgumentException("Date from is less than residence is available from.");
        }
        if(dateTo.isAfter(residence.getAvailableUntil())){
            throw new IllegalArgumentException("Date to is more than residence is available until.");
        }

        Integer totalWeeks = toIntExact(Duration.between(dateFrom.atTime(0, 0), dateTo.atTime(0, 0)).toDays() / 7);
        Integer amountToSpend = totalWeeks * residence.getWeeklyPrice();

        if(extraServices != null && !extraServices.isEmpty()){
            for (ExtraService service : extraServices){
                amountToSpend += service.getPrice();
            }
        }

        if(user.getBalance() < amountToSpend){
            throw new IllegalArgumentException("Balance is too low.");
        }

        Reservation reservation = new Reservation(user, residence, dateFrom, dateTo);
        reservation.setAmountSpent(amountToSpend);
        reservation.setSelectedExtraServices(extraServices);

        user.setBalance(user.getBalance() - amountToSpend);

        reservationDAO.save(reservation);
        userDAO.update(user);
    }

    @Override
    public void cancel(Reservation reservation) {
        Objects.requireNonNull(reservation);

        if(LocalDate.now().isAfter(reservation.getDateFrom())){
            Integer weeksPassedAlready = toIntExact(Duration.between(LocalDate.now().with(DayOfWeek.MONDAY).atTime(0, 0),
                    reservation.getDateTo().atTime(0, 0)).toDays() / 7);
            Integer totalWeeks = toIntExact(Duration.between(reservation.getDateFrom().atTime(0, 0), reservation.getDateTo().atTime(0, 0)).toDays() / 7);

            if(totalWeeks == weeksPassedAlready){
                fullyRefund(reservation);
            }
            else{
                Integer amountToRefund = reservation.getAmountSpent() * weeksPassedAlready / totalWeeks;

                User user = reservation.getUser();
                user.setBalance(user.getBalance() + amountToRefund);

                reservation.setAmountSpent(reservation.getAmountSpent() - amountToRefund);
                reservation.setDateTo(LocalDate.now().with(DayOfWeek.SUNDAY));

                userDAO.update(user);
                reservationDAO.update(reservation);
            }
        }
        else{
            fullyRefund(reservation);
        }
    }

    private void fullyRefund(Reservation reservation){
        User user = reservation.getUser();
        user.setBalance(user.getBalance() + reservation.getAmountSpent());

        userDAO.update(user);
        reservationDAO.remove(reservation);
    }
}
