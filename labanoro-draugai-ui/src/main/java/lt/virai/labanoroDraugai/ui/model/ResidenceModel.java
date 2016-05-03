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
    private String image;
    private Integer weeklyPrice;
    private WeekRangeModel availability;

    public ResidenceModel(Residence residence) {
        id = residence.getId();
        capacity = residence.getCapacity();
        address = residence.getAddress();
        city = new CityModel(residence.getCity());
        image = residence.getImage();
/*        weeklyPrice = residence.getWeeklyPrice();*/
    }

    @Override
    public Residence mapTo() {
        Residence residence = new Residence(capacity, address, city.mapTo(), weeklyPrice, availability.getDateFrom());
        residence.setId(id);
        residence.setImage(image);
        residence.setAvailableUntil(availability.getDateTo());

        return residence;
    }

    @Override
    public ModelState validate() {
        ModelState modelState = new ModelState();

        if(capacity < 1)
            modelState.addError("capacity", "Capacity cannot be less than 1.");
        if(address.length() < 4)
            modelState.addError("address", "Address cannot be shorter than 4.");
        if(city == null)
            modelState.addError("city", "City is required.");
        else
            modelState.merge(city.validate(), "city");
        if(image != null && image.startsWith("data:image"))
            modelState.addError("image", "File type not supported.");
        if(weeklyPrice == null)
            modelState.addError("weeklyPrice", "Weekly price is required.");
        else if(weeklyPrice < 1)
            modelState.addError("weeklyPrice", "Weekly price cannot be less than 1.");
        if(availability == null)
            modelState.addError("availability", "Availability dates are required.");
        else
            modelState.merge(availability.validate(), "availability");

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

    public void setAvailability(WeekRangeModel availability) {
        this.availability = availability;
    }

    public WeekRangeModel getAvailability() {
        return availability;
    }
}
