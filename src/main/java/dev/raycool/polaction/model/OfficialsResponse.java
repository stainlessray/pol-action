package dev.raycool.polaction.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OfficialsResponse {
    private PoliticalOfficial[] officials;

    public PoliticalOfficial[] getOfficials() {
        return officials;
    }

    public void setOfficials(PoliticalOfficial[] officials) {
        this.officials = officials;
    }
}
