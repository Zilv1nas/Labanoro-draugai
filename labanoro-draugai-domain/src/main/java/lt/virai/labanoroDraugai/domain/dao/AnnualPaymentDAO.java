package lt.virai.labanoroDraugai.domain.dao;

import lt.virai.labanoroDraugai.domain.entities.AnnualPayment;

/**
 * Created by Žilvinas on 2016-05-19.
 */
public interface AnnualPaymentDAO extends DAO<AnnualPayment> {
    AnnualPayment getLatestAnnualPayment(int userId);
}
