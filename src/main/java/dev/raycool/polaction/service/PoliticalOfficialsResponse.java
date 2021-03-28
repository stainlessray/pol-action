package dev.raycool.polaction.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.raycool.polaction.officesmodels.PoliticalOffice;
import dev.raycool.polaction.officialsmodels.PoliticalOfficial;


@JsonIgnoreProperties(ignoreUnknown = true)
public class PoliticalOfficialsResponse {

    private PoliticalOfficial[] officials;
    public PoliticalOfficial[] getOfficials() {
        return officials;
    }
    public void setOfficials(PoliticalOfficial[] officials) {
        this.officials = officials;
    }

    private PoliticalOffice[] offices;
    public PoliticalOffice[] getOffices() {
        return offices;
    }
    public void setOffices(PoliticalOffice[] offices) {
        this.offices = offices;
    }
}
