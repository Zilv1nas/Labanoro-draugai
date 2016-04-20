package lt.virai.labanoroDraugai.domain.entities;

import javax.persistence.*;

/**
 * Created by Mantas on 4/13/2016.
 */
@Entity
@Table(name="city", schema = "labanoro-draugai-db", catalog = "")
public class City {
    private Integer id;
    private String name;

    public City(String name) {
        this.name = name;
    }

    protected City() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;

    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
