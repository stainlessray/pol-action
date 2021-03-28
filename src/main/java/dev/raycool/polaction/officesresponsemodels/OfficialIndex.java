package dev.raycool.polaction.officesresponsemodels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OfficialIndex {
    private Integer officialIndices;

    public OfficialIndex() {
    }

    public OfficialIndex(Integer officialIndices) {
        this.officialIndices = officialIndices;
    }

    public Integer getOfficialIndices() {
        return officialIndices;
    }

    public void setOfficialIndices(Integer officialIndices) {
        this.officialIndices = officialIndices;
    }
}
