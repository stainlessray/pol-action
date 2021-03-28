package dev.raycool.polaction.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.raycool.polaction.officesresponsemodels.PoliticalOffice;
import dev.raycool.polaction.officialsresponsemodels.PoliticalOfficial;


@JsonIgnoreProperties(ignoreUnknown = true)
public class PoliticalOfficialsResponse {

    private PoliticalOfficial[] officials;
    private PoliticalOffice[] offices;

    public PoliticalOfficial[] getOfficials() {
        return officials;
    }
    public void setOfficials(PoliticalOfficial[] officials) {
        this.officials = officials;
    }

    public PoliticalOffice[] getOffices() {
        return offices;
    }
    public void setOffices(PoliticalOffice[] offices) {
        this.offices = offices;
    }
}
