package dev.raycool.polaction.officesresponsemodels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OfficialIndex {
    private Integer officialIndex;

    public OfficialIndex() {
    }

    public OfficialIndex(Integer officialIndex) {
        this.officialIndex = officialIndex;
    }

    public Integer getOfficialIndex() {
        return officialIndex;
    }

    public void setOfficialIndex(Integer officialIndex) {
        this.officialIndex = officialIndex;
    }
}
