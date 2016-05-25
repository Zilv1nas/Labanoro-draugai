package lt.virai.labanoroDraugai.ui.model.residence;

import lt.virai.labanoroDraugai.domain.entities.ExtraService;
import lt.virai.labanoroDraugai.ui.model.MappableTo;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import java.util.Objects;

/**
 * Created by Žilvinas on 2016-05-11.
 */
public class ExtraServiceModel implements MappableTo<ExtraService> {
    private Integer id;
    @NotEmpty(message = "Vardas negali būti tuščias")
    private String name;
    @Range(min = 0, message = "Kaina negali būti neigiama")
    private Integer price;

    public ExtraServiceModel() {
    }

    public ExtraServiceModel(ExtraService extraService) {
        this.id = extraService.getId();
        this.name = extraService.getName();
        this.price = extraService.getPrice();
    }

    @Override
    public ExtraService mapTo() {
        ExtraService extraService = new ExtraService();
        extraService.setId(id);
        extraService.setName(name);
        extraService.setPrice(price);
        return  extraService;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExtraServiceModel that = (ExtraServiceModel) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
