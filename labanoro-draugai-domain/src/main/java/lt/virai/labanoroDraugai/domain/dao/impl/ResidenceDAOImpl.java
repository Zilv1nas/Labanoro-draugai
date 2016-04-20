package lt.virai.labanoroDraugai.domain.dao.impl;

import lt.virai.labanoroDraugai.domain.dao.AbstractDAO;
import lt.virai.labanoroDraugai.domain.dao.ResidenceDAO;
import lt.virai.labanoroDraugai.domain.entities.Residence;

import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by Mantas on 4/19/2016.
 */
@Stateless
public class ResidenceDAOImpl extends AbstractDAO<Residence> implements ResidenceDAO {
    @Override
    public List<Residence> getAll() {
        return streams.streamAll(entityManager, Residence.class).toList();
    }
}
