package lt.virai.labanoroDraugai.ui.model;

import lt.virai.labanoroDraugai.domain.entities.Residence;

/**
 * Created by Mantas on 4/20/2016.
 */
public class ResidenceModel implements ValidatableModel, MappableTo<Residence> {
    private Integer id;
    private Integer capacity;
    private String address;
    private CityModel city;

    public ResidenceModel(Residence residence) {
        id = residence.getId();
        capacity = residence.getCapacity();
        address = residence.getAddress();
        city = new CityModel(residence.getCity());
    }

    @Override
    public Residence mapTo() {
        Residence residence = new Residence(capacity, address, city.mapTo());
        residence.setId(id);

        return residence;
    }

    @Override
    public ModelState validate() {
        ModelState modelState = new ModelState();

        if(capacity < 1)
            modelState.addError("capacity", "Capacity cannot be less than 1.");
        if(address.length() < 4)
            modelState.addError("address", "Address cannot be less than 4.");
        if(city == null)
            modelState.addError("city", "City is required.");

        return modelState;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CityModel getCity() {
        return city;
    }

    public void setCity(CityModel city) {
        this.city = city;
    }
}
