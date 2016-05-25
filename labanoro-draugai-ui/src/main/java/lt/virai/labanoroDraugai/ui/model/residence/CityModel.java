package lt.virai.labanoroDraugai.ui.model.residence;

import lt.virai.labanoroDraugai.domain.entities.City;
import lt.virai.labanoroDraugai.ui.model.MappableTo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Mantas on 4/19/2016.
 */
public class CityModel implements MappableTo<City> {
    private Integer id;

    @NotNull(message = "Vardas negali būti tuščias")
    @Size(min = 4, message = "Vardas turi būti bent iš 4 simbolių")
    private String name;

    public CityModel(City city) {
        name = city.getName();
        id = city.getId();
    }

    public CityModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public City mapTo() {
        City city = new City(name);
        city.setId(getId());

        return city;
    }
}
