package dev.raycool.polaction.api.offices.model.response;

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

    @Override
    public String toString() {
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
