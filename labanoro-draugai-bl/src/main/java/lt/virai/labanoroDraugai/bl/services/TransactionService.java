package lt.virai.labanoroDraugai.bl.services;

import lt.virai.labanoroDraugai.bl.exceptions.LabanoroException;
import lt.virai.labanoroDraugai.domain.entities.PointPurchase;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Žilvinas on 2016-05-10.
 */
public interface TransactionService {
    PointPurchase createPurchase(Integer userId, Integer amount);

    List<PointPurchase> getAllPurchases();

    void confirmPurchase(int purchaseId);

    void rejectPurchase(int purchaseId);

    boolean hasUserPaidAnnualPayment(int userId);

    LocalDate getLastAnnualPaymentDate(int userId);

    void payAnnualPayment(Integer userId) throws LabanoroException;
}
