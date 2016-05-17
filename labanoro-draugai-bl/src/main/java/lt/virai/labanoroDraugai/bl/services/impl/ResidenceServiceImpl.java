package lt.virai.labanoroDraugai.bl.services.impl;

import lt.virai.labanoroDraugai.bl.services.ResidenceService;
import lt.virai.labanoroDraugai.domain.dao.ResidenceDAO;
import lt.virai.labanoroDraugai.domain.entities.Residence;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Created by Mantas on 4/20/2016.
 */
@Stateless
public class ResidenceServiceImpl implements ResidenceService {
    @Inject
    private ResidenceDAO residenceDAO;

    @Override
    public Residence create(Residence residence) {
        return residenceDAO.save(residence);
    }
}
