package dev.raycool.polaction.officialsmodels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.raycool.polaction.officesmodels.PoliticalOffice;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PoliticalOfficialsResponse {
    private PoliticalOfficial[] officials;

    public PoliticalOfficial[] getOfficials() {
        return officials;
    }

    public void setOfficials() {
        setOfficials();
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
