package dev.raycool.polaction.officesmodels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OfficialIndex {
    private Integer officialIndices;

    public Integer getOfficialIndices() {
        return officialIndices;
    }

    public void setOfficialIndices(Integer officialIndices) {
        this.officialIndices = officialIndices;
    }

    @Override
    public String toString() {
        return "OfficialIndex{" +
                "officialIndices=" + officialIndices +
                '}';
    }
}
