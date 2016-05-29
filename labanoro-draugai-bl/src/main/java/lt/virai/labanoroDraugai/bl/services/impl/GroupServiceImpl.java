package lt.virai.labanoroDraugai.bl.services.impl;

import lt.virai.labanoroDraugai.bl.services.GroupService;
import lt.virai.labanoroDraugai.domain.dao.ReservationDAO;
import lt.virai.labanoroDraugai.domain.dao.UserDAO;
import lt.virai.labanoroDraugai.domain.dao.UserGroupDAO;
import lt.virai.labanoroDraugai.domain.entities.Reservation;
import lt.virai.labanoroDraugai.domain.entities.User;
import lt.virai.labanoroDraugai.domain.entities.UserGroup;
import lt.virai.labanoroDraugai.domain.model.UserGroupSettings;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.Math.toIntExact;

/**
 * Created by Mantas on 5/29/2016.
 */
@Stateless
public class GroupServiceImpl implements GroupService {

    @Inject
    private UserDAO userDAO;
    @Inject
    private UserGroupDAO userGroupDAO;
    @Inject
    private ReservationDAO reservationDAO;

    @Override
    public UserGroupSettings getGroupSettings() {
        Integer interval = userGroupDAO.getFirstUserGroup().getDaysRequirement();
        Integer groupsCount = userGroupDAO.getAll().size();

        return new UserGroupSettings(groupsCount, interval);
    }

    @Override
    public void updateGroups(UserGroupSettings settings) {
        Objects.requireNonNull(settings);

        Integer groupsNumber = settings.getGroupsNumber();
        Integer daysInterval = settings.getDaysInterval();

        Objects.requireNonNull(groupsNumber);
        Objects.requireNonNull(daysInterval);

        List<User> users = userDAO.getAllVerifiedMembers();

        for (User user : users) {
            user.setUserGroup(null);
            userDAO.update(user);
        }

        List<UserGroup> currentGroups = userGroupDAO.getAll();

        for (UserGroup existingGroup : currentGroups) {
            userGroupDAO.remove(existingGroup);
        }

        Integer priority;

        for (priority = 1; priority <= groupsNumber; priority++) {
            UserGroup group;
            if (priority.equals(groupsNumber)) {
                group = new UserGroup(null, priority);
            } else {
                group = new UserGroup(daysInterval * priority, priority);
            }
            userGroupDAO.save(group);
        }

        List<UserGroup> updatedGroups = userGroupDAO.getAll()
                .stream().sorted((e1, e2) ->
                        e1.getPriority().compareTo(e2.getPriority()))
                .collect(Collectors.toList());

        LocalDate dateFrom = LocalDate.now().withDayOfYear(1).minusYears(1);
        LocalDate dateTo = LocalDate.now().withDayOfYear(1);

        for (User user : users) {
            List<Reservation> lastYearReservations
                    = reservationDAO.getReservationsForUser(user)
                    .stream().filter(e ->
                            e.getDateFrom().isBefore(dateTo)
                                    && e.getDateTo().isAfter(dateFrom)).collect(Collectors.toList());

            Integer daysReserved = 0;

            for (Reservation reservation : lastYearReservations) {
                daysReserved += calculateDays(reservation.getDateFrom().isAfter(dateFrom) ? reservation.getDateFrom() : dateFrom,
                        reservation.getDateTo().isBefore(dateTo) ? reservation.getDateTo() : dateTo);
            }

            UserGroup groupToSet = null;
            for (UserGroup userGroup : updatedGroups ) {
                if(userGroup.getDaysRequirement() != null && userGroup.getDaysRequirement() > daysReserved){
                    groupToSet = userGroup;
                    break;
                }
            }

            if(groupToSet == null){
                groupToSet = updatedGroups.stream().filter(a -> a.getDaysRequirement() == null).findFirst().get();
            }

            user.setUserGroup(groupToSet);
            userDAO.update(user);
        }
    }

    private Integer calculateDays(LocalDate from, LocalDate to){
        return toIntExact(Duration.between(from.atStartOfDay(),
                to.atStartOfDay()).toDays());
    }
}
