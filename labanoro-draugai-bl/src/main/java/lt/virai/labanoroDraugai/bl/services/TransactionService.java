package lt.virai.labanoroDraugai.bl.services;

import lt.virai.labanoroDraugai.domain.entities.PointPurchase;

import java.util.List;

/**
 * Created by Žilvinas on 2016-05-10.
 */
public interface TransactionService {
    void createPurchase(Integer userId, Integer amount);

    List<PointPurchase> getAllPurchases();
}
