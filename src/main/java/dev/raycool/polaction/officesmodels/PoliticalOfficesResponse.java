package dev.raycool.polaction.officesmodels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
