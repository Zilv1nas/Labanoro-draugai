package lt.virai.labanoroDraugai.ui.model.residence;

import lt.virai.labanoroDraugai.domain.entities.Residence;
import lt.virai.labanoroDraugai.ui.model.MappableTo;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Mantas on 4/20/2016.
 */
public class ResidenceModel implements MappableTo<Residence> {
    private Integer id;

    @NotEmpty
    @Size(min = 4, message = "Address cannot be shorter than 4.")
    private String name;

    @NotEmpty
    private String description;

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
    private Integer dailyPrice;

    @NotNull(message = "Availability dates are required.")
    @Valid
    private WeekRangeModel availability;

    @Valid
    private Set<ExtraServiceModel> extraServices = new HashSet<>();

    public ResidenceModel(Residence residence) {
        id = residence.getId();
        capacity = residence.getCapacity();
        name = residence.getName();
        description = residence.getDescription();
        address = residence.getAddress();
        city = new CityModel(residence.getCity());
        image = residence.getImage();
        dailyPrice = residence.getDailyPrice();
        extraServices = residence.getExtraServices().stream().map(ExtraServiceModel::new).collect(Collectors.toSet());
    }

    @Override
    public Residence mapTo() {
        Residence residence = new Residence();
        residence.setId(id);
        residence.setName(name);
        residence.setCapacity(capacity);
        residence.setAddress(address);
        residence.setCity(city.mapTo());
        residence.setDailyPrice(dailyPrice);
        residence.setDescription(description);
        residence.setImage(image);
        residence.setAvailableFrom(availability.getDateFrom());
        residence.setAvailableUntil(availability.getDateTo());
        residence.setExtraServices(extraServices.stream().map(ExtraServiceModel::mapTo).collect(Collectors.toSet()));
        return residence;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getDailyPrice() {
        return dailyPrice;
    }

    public void setDailyPrice(Integer dailyPrice) {
        this.dailyPrice = dailyPrice;
    }

    public WeekRangeModel getAvailability() {
        return availability;
    }

    public void setAvailability(WeekRangeModel availability) {
        this.availability = availability;
    }

    public Set<ExtraServiceModel> getExtraServices() {
        return extraServices;
    }

    public void setExtraServices(Set<ExtraServiceModel> extraServices) {
        this.extraServices = extraServices;
    }
}
