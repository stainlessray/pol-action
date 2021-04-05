package dev.raycool.polaction.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.raycool.polaction.officesresponse.NormalizedInput;
import dev.raycool.polaction.officesresponse.PoliticalOffice;
import dev.raycool.polaction.officialsresponse.PoliticalOfficial;


@JsonIgnoreProperties(ignoreUnknown = true)
public class PoliticalOfficialsResponse {

    private PoliticalOfficial[] officials;
    private PoliticalOffice[] offices;
    private NormalizedInput normalizedInput;


    public NormalizedInput getNormalizedInput() { return normalizedInput; }
    public void setNormalizedInput(NormalizedInput normalizedInput) { this.normalizedInput = normalizedInput; }

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

