package lt.virai.labanoroDraugai.domain.model.search;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by Žilvinas on 2016-05-23.
 */
public class ResidenceSearchRequest implements Serializable {
    private static final long serialVersionUID = 8821244731931120877L;

    private int page = 1;
    private int pageSize = 5;
    private String title;
    private String address;
    private Integer priceFrom;
    private Integer priceTo;
    private LocalDate unoccupiedFrom;
    private LocalDate unoccupiedTo;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(Integer priceFrom) {
        this.priceFrom = priceFrom;
    }

    public Integer getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(Integer priceTo) {
        this.priceTo = priceTo;
    }

    public LocalDate getUnoccupiedFrom() {
        return unoccupiedFrom;
    }

    public void setUnoccupiedFrom(LocalDate unoccupiedFrom) {
        this.unoccupiedFrom = unoccupiedFrom;
    }

    public LocalDate getUnoccupiedTo() {
        return unoccupiedTo;
    }

    public void setUnoccupiedTo(LocalDate unoccupiedTo) {
        this.unoccupiedTo = unoccupiedTo;
    }
}
