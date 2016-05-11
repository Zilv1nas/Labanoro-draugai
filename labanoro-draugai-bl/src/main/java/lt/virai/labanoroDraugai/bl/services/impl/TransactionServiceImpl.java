package lt.virai.labanoroDraugai.bl.services.impl;

import lt.virai.labanoroDraugai.bl.services.TransactionService;
import lt.virai.labanoroDraugai.domain.dao.PointPurchaseDAO;
import lt.virai.labanoroDraugai.domain.dao.UserDAO;
import lt.virai.labanoroDraugai.domain.entities.PointPurchase;
import lt.virai.labanoroDraugai.domain.entities.User;

import javax.inject.Inject;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by Žilvinas on 2016-05-10.
 */
public class TransactionServiceImpl implements TransactionService {

    @Inject
    private UserDAO userDAO;

    @Inject
    private PointPurchaseDAO pointPurchaseDAO;

    @Override
    public PointPurchase createPurchase(Integer userId, Integer amount) {
        Objects.requireNonNull(userId);
        Objects.requireNonNull(amount);

        Optional<User> user = Optional.ofNullable(userDAO.get(userId));
        if (user.isPresent()) {
            PointPurchase pp = new PointPurchase(user.get(), amount);
            pointPurchaseDAO.save(pp);
            return pp;
        } else {
            return null;
        }
    }

    @Override
    public List<PointPurchase> getAllPurchases() {
        return pointPurchaseDAO.getAll();
    }
}
