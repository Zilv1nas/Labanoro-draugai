package lt.virai.labanoroDraugai.domain.dao;

import lt.virai.labanoroDraugai.domain.entities.Residence;
import lt.virai.labanoroDraugai.domain.model.search.ResidenceSearchRequest;
import lt.virai.labanoroDraugai.domain.model.search.ResidenceSearchResponse;

/**
 * Created by Mantas on 4/19/2016.
 */
public interface ResidenceDAO extends DAO<Residence> {
    ResidenceSearchResponse searchResidences(ResidenceSearchRequest request);
}
