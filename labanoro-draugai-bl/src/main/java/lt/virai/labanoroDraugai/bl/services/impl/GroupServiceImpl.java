package lt.virai.labanoroDraugai.bl.services.impl;

import lt.virai.labanoroDraugai.bl.services.GroupService;
import lt.virai.labanoroDraugai.domain.dao.ReservationDAO;
import lt.virai.labanoroDraugai.domain.dao.UserDAO;

import javax.inject.Inject;
import java.util.Objects;

/**
 * Created by Mantas on 5/29/2016.
 */
public class GroupServiceImpl implements GroupService {

    @Inject
    UserDAO userDAO;
    @Inject
    ReservationDAO reservationDAO;

    @Override
    public void getGroupSettings() {

    }

    @Override
    public void updateClubSettings(Integer groupsNumber, Integer daysInterval) {
        Objects.requireNonNull(groupsNumber);
        Objects.requireNonNull(daysInterval);
    }
}
