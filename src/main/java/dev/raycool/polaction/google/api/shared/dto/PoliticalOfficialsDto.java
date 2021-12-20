package dev.raycool.polaction.google.api.shared.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.raycool.polaction.google.api.offices.model.response.NormalizedInput;
import dev.raycool.polaction.google.api.offices.model.response.PoliticalOffice;
import dev.raycool.polaction.google.api.officials.model.response.PoliticalOfficial;


@JsonIgnoreProperties(ignoreUnknown = true)
public class PoliticalOfficialsDto {

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

