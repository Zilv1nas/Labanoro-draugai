package lt.virai.labanoroDraugai.ui.model.residence;

import lt.virai.labanoroDraugai.domain.entities.Residence;
import lt.virai.labanoroDraugai.ui.model.MappableTo;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Mantas on 4/20/2016.
 */
public class ResidenceModel implements MappableTo<Residence> {
    private Integer id;

    @NotEmpty(message = "Pavadinimas negali būti tuščias")
    @Size(min = 4, message = "Pavadinimas negali būti trumpesnis nei 4 simboliai")
    private String name;

    @NotEmpty(message = "Aprašymas negali būti tuščias")
    private String description;

    @NotNull(message = "Laisvos vietos negali būti tuščios")
    @Min(value = 1, message = "Laisvų vietų negali būti mažiau nei 1")
    private Integer capacity;

    @NotEmpty(message = "Adresas negali būti tuščias")
    @Size(min = 4, message = "Adresas negali būti trumpesnis nei 4 simboliai")
    private String address;

    private CityModel city;

    @Pattern(regexp = "^data:image.*", message = "Nepalaikomas failo tipas")
    private String image;

    @NotNull(message = "Savaitinė kaina negali būti tuščia")
    @Min(value = 1, message = "Savaitinė kaina negali būti mažiau nei 1")
    private Integer weeklyPrice;

    @NotNull(message = "Turi būti nurodytos užsakymo datos")
    @Valid
    private WeekRangeModel availability;

    private LocalDate dateOfRegistration;

    private int version;

    @Valid
    private Set<ExtraServiceModel> extraServices = new HashSet<>();

    public ResidenceModel() {
    }

    public ResidenceModel(Residence residence) {
        id = residence.getId();
        capacity = residence.getCapacity();
        name = residence.getName();
        description = residence.getDescription();
        address = residence.getAddress();
        city = Optional.ofNullable(residence.getCity()).map(CityModel::new).orElse(null);
        image = residence.getImage();
        weeklyPrice = residence.getWeeklyPrice();
        availability = new WeekRangeModel (residence.getAvailableFrom(), residence.getAvailableUntil());
        extraServices = residence.getExtraServices().stream().map(ExtraServiceModel::new).collect(Collectors.toSet());
        version = residence.getVersion();
        dateOfRegistration = residence.getDateOfRegistration();
    }

    @Override
    public Residence mapTo() {
        Residence residence = new Residence();
        residence.setId(id);
        residence.setName(name);
        residence.setCapacity(capacity);
        residence.setAddress(address);
        residence.setCity(city != null ? city.mapTo() : null);
        residence.setWeeklyPrice(weeklyPrice);
        residence.setDescription(description);
        residence.setImage(image);
        if (availability != null) {
            residence.setAvailableFrom(availability.getDateFrom());
            residence.setAvailableUntil(availability.getDateTo());
        }
        residence.setExtraServices(extraServices.stream().map(ExtraServiceModel::mapTo).collect(Collectors.toSet()));
        residence.setVersion(version);
        residence.setDateOfRegistration(dateOfRegistration);
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

    public Integer getWeeklyPrice() {
        return weeklyPrice;
    }

    public void setWeeklyPrice(Integer weeklyPrice) {
        this.weeklyPrice = weeklyPrice;
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

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public LocalDate getDateOfRegistration() {
        return dateOfRegistration;
    }
}
