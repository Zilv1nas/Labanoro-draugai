package lt.virai.labanoroDraugai.bl.services.impl;

import lt.virai.labanoroDraugai.domain.model.search.ResidenceSearchRequest;
import lt.virai.labanoroDraugai.bl.services.ResidenceService;
import lt.virai.labanoroDraugai.domain.dao.ResidenceDAO;
import lt.virai.labanoroDraugai.domain.entities.Residence;
import lt.virai.labanoroDraugai.domain.model.search.ResidenceSearchResponse;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Objects;

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

    @Override
    public void update(Residence residence) {
        residenceDAO.update(Objects.requireNonNull(residence));
    }

    @Override
    public List<Residence> getAll() {
        return residenceDAO.getAll();
    }

    @Override
    public Residence get(Integer id) {
        return residenceDAO.get(Objects.requireNonNull(id));
    }

    @Override
    public void remove(Integer id) {
        residenceDAO.remove(Objects.requireNonNull(id));
    }

    @Override
    public ResidenceSearchResponse searchResidences(ResidenceSearchRequest request) {
        return residenceDAO.searchResidences(Objects.requireNonNull(request));
    }
}
