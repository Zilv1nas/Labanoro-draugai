package lt.virai.labanoroDraugai.domain.dao.impl;

import lt.virai.labanoroDraugai.domain.dao.AbstractDAO;
import lt.virai.labanoroDraugai.domain.dao.CityDAO;
import lt.virai.labanoroDraugai.domain.entities.City;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * Created by Mantas on 4/19/2016.
 */
@Stateless
public class CityDAOImpl extends AbstractDAO<City> implements CityDAO {
    public List<City> getAll(){
        return streams.streamAll(entityManager, City.class).toList();
    }
}
