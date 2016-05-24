package lt.virai.labanoroDraugai.domain.model.search;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Žilvinas on 2016-05-23.
 */
public class ResidenceSearchResponse {
    private long total;
    private List<ResidenceListModel> residences = new ArrayList<>();

    public ResidenceSearchResponse() {
    }

    public ResidenceSearchResponse(List<ResidenceListModel> reidences, long total) {
        this.residences = reidences;
        this.total = total;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<ResidenceListModel> getResidences() {
        return residences;
    }

    public void setResidences(List<ResidenceListModel> residences) {
        this.residences = residences;
    }
}
