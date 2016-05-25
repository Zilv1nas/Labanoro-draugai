package lt.virai.labanoroDraugai.bl.services.impl;

import lt.virai.labanoroDraugai.bl.exceptions.LabanoroException;
import lt.virai.labanoroDraugai.bl.interceptors.binding.Logged;
import lt.virai.labanoroDraugai.bl.services.TransactionService;
import lt.virai.labanoroDraugai.domain.dao.AnnualPaymentDAO;
import lt.virai.labanoroDraugai.domain.dao.ClubSettingDAO;
import lt.virai.labanoroDraugai.domain.dao.PointPurchaseDAO;
import lt.virai.labanoroDraugai.domain.dao.UserDAO;
import lt.virai.labanoroDraugai.domain.entities.AnnualPayment;
import lt.virai.labanoroDraugai.domain.entities.PointPurchase;
import lt.virai.labanoroDraugai.domain.entities.User;
import lt.virai.labanoroDraugai.domain.model.ClubSettingName;
import lt.virai.labanoroDraugai.domain.model.PurchaseStatus;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by Žilvinas on 2016-05-10.
 */
@Stateless
public class TransactionServiceImpl implements TransactionService {

    @Inject
    private UserDAO userDAO;

    @Inject
    private PointPurchaseDAO pointPurchaseDAO;

    @Inject
    private AnnualPaymentDAO annualPaymentDAO;

    @Inject
    private ClubSettingDAO clubSettingDAO;

    @Logged
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

    @Logged
    @Override
    public void confirmPurchase(int purchaseId) {
        Optional.ofNullable(pointPurchaseDAO.get(purchaseId)).ifPresent(p -> {
            if (p.getStatus() == PurchaseStatus.PENDING) {
                User user = p.getUser();
                user.setBalance(user.getBalance() + p.getAmount());
                p.setStatus(PurchaseStatus.COMPLETED);
            }
        });
    }

    @Logged
    @Override
    public void rejectPurchase(int purchaseId) {
        Optional.ofNullable(pointPurchaseDAO.get(purchaseId)).ifPresent(p -> {
            if (p.getStatus() == PurchaseStatus.PENDING) {
                p.setStatus(PurchaseStatus.DENIED);
            }
        });
    }

    @Override
    public boolean hasUserPaidAnnualPayment(int userId) {
        AnnualPayment annualPayment = annualPaymentDAO.getLatestAnnualPayment(userId);
        return annualPayment != null && Period.between(annualPayment.getPaymentDate(), LocalDate.now()).getYears() < 1;
    }

    @Override
    public LocalDate getLastAnnualPaymentDate(int userId) {
        return Optional.ofNullable(annualPaymentDAO.getLatestAnnualPayment(userId))
                .map(AnnualPayment::getPaymentDate)
                .orElse(null);
    }

    @Logged
    @Override
    public void payAnnualPayment(Integer userId) throws LabanoroException {
        Objects.requireNonNull(userId);

        int paymentSize = Optional.ofNullable(clubSettingDAO.getSetting(ClubSettingName.ANNUAL_PAYMENT_SIZE))
                .map(s -> Integer.parseInt(s.getValue())).orElseThrow(IllegalStateException::new);

        User user = Optional.ofNullable(userDAO.get(userId)).orElseThrow(IllegalStateException::new);
        if (user.getBalance() < paymentSize) {
            throw new LabanoroException("Balance is too low");
        }

        user.setBalance(user.getBalance() - paymentSize);
        AnnualPayment payment = new AnnualPayment();
        payment.setAmount(paymentSize);
        payment.setPaymentDate(LocalDate.now());
        payment.setUser(user);
        user.getAnnualPayments().add(payment);

        userDAO.update(user);
    }

    @Logged
    @Override
    public void sendPointsToUser(Integer userId, Integer amount) {
        Objects.requireNonNull(amount);
        Optional.ofNullable(userDAO.get(Objects.requireNonNull(userId)))
                .ifPresent(u -> u.setBalance(u.getBalance() + amount));
    }
}
