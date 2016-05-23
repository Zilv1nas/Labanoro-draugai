package lt.virai.labanoroDraugai.domain.dao.impl;

import lt.virai.labanoroDraugai.domain.dao.AbstractDAO;
import lt.virai.labanoroDraugai.domain.dao.AnnualPaymentDAO;
import lt.virai.labanoroDraugai.domain.entities.AnnualPayment;

/**
 * Created by Žilvinas on 2016-05-19.
 */
public class AnnualPaymentDAOImpl extends AbstractDAO<AnnualPayment> implements AnnualPaymentDAO {
    @Override
    public AnnualPayment getLatestAnnualPayment(int userId) {
        return streams.streamAll(entityManager, AnnualPayment.class).sortedDescendingBy(AnnualPayment::getPaymentDate).findFirst().orElse(null);
    }
}
