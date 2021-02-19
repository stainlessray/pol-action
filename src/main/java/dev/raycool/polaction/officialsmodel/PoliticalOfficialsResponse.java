package dev.raycool.polaction.officialsmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PoliticalOfficialsResponse {
    private PoliticalOfficial[] officials;

    public PoliticalOfficial[] getOfficials() {
        return officials;
    }

    public void setOfficials(PoliticalOfficial[] officials) {
        this.officials = officials;
    }
}
