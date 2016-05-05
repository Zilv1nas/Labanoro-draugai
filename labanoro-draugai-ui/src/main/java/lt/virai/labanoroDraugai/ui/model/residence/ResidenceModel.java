package lt.virai.labanoroDraugai.ui.model.residence;

import lt.virai.labanoroDraugai.domain.entities.Residence;
import lt.virai.labanoroDraugai.ui.model.MappableTo;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by Mantas on 4/20/2016.
 */
public class ResidenceModel implements MappableTo<Residence> {
    private Integer id;

    @Min(value = 1, message = "Capacity cannot be less than 1.")
    private Integer capacity;

    @Size(min = 4, message = "Address cannot be shorter than 4.")
    private String address;

    @NotNull(message = "City is required")
    @Valid
    private CityModel city;

    @Pattern(regexp = "^data:image.*", message = "File type not supported.")
    private String image;

    @NotNull(message = "Weekly price is required.")
    @Min(value = 1, message = "Weekly price cannot be less than 1.")
    private Integer weeklyPrice;

    @NotNull(message = "Availability dates are required.")
    @Valid
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
