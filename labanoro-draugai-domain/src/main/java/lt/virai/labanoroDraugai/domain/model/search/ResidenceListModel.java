package lt.virai.labanoroDraugai.domain.model.search;

import java.io.Serializable;

/**
 * Created by Žilvinas on 2016-05-23.
 */
public class ResidenceListModel implements Serializable {
    private static final long serialVersionUID = 1221054261488684484L;

    private Integer id;
    private String name;
    private String description;
    private Integer capacity;
    private String address;
    private String image;
    private Integer weeklyPrice;

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
}
