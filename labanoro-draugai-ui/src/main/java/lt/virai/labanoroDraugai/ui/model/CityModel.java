package lt.virai.labanoroDraugai.ui.model;

import lt.virai.labanoroDraugai.domain.entities.City;

import javax.enterprise.inject.Model;

/**
 * Created by Mantas on 4/19/2016.
 */
public class CityModel implements ValidatableModel, MappableFrom<City>, MappableTo<City> {
    private Integer id;
    private String name;

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
    public ModelState validate() {
        ModelState modelState = new ModelState();
        if (name == null || name.length() == 0)
            modelState.addError("name", "Name is required");
        else if (name.length() < 4)
            modelState.addError("name", "Name cannot be shorter than 4 symbols");

        return modelState;
    }

    @Override
    public void mapFrom(City city) {
        name = city.getName();
        id = city.getId();
    }

    @Override
    public City mapTo() {
        City city = new City(name);
        city.setId(getId());

        return city;
    }
}
