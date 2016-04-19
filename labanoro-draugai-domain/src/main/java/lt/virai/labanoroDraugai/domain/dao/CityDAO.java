package lt.virai.labanoroDraugai.domain.dao;

import lt.virai.labanoroDraugai.domain.entities.City;
import org.hibernate.persister.collection.QueryableCollection;

import java.util.List;

/**
 * Created by Mantas on 4/19/2016.
 */
public interface CityDAO extends DAO<City> {
    List<City> getAll();
}
