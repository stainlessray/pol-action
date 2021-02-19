package dev.raycool.polaction.officesmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.raycool.polaction.officesmodel.PoliticalOffice;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PoliticalOfficesResponse {
    private PoliticalOffice[] offices;

    public PoliticalOffice[] getOffices() {
        return offices;
    }

    public void setOffices(PoliticalOffice[] offices) {
        this.offices = offices;
    }
}
