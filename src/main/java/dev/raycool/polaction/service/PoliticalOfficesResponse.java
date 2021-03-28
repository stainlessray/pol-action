package dev.raycool.polaction.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.raycool.polaction.officesmodels.PoliticalOffice;

@Deprecated
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
