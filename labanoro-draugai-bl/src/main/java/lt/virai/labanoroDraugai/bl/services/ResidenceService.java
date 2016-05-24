package lt.virai.labanoroDraugai.bl.services;

import lt.virai.labanoroDraugai.domain.entities.Residence;
import lt.virai.labanoroDraugai.domain.model.search.ResidenceSearchRequest;
import lt.virai.labanoroDraugai.domain.model.search.ResidenceSearchResponse;

import java.util.List;

/**
 * Created by Mantas on 4/20/2016.
 */
public interface ResidenceService {
    Residence create(Residence residence);

    void update(Residence residence);

    List<Residence> getAll();

    Residence get(Integer id);

    void remove(Integer id);

    ResidenceSearchResponse searchResidences(ResidenceSearchRequest request);
}
