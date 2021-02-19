package dev.raycool.polaction.officesmodels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PoliticalOffice {

    private String name;
    private String divisionId;
    private Level[] levels;
    private Role[] roles;
    private OfficialIndex[] officialIndices;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(String divisionId) {
        this.divisionId = divisionId;
    }

    public Level[] getLevels() {
        return levels;
    }

    public void setLevels(Level[] levels) {
        this.levels = levels;
    }

    public Role[] getRoles() {
        return roles;
    }

    public void setRoles(Role[] roles) {
        this.roles = roles;
    }

    public OfficialIndex[] getOfficialIndices() {
        return officialIndices;
    }

    public void setOfficialIndices(OfficialIndex[] officialIndices) {
        this.officialIndices = officialIndices;
    }

    @Override
    public String toString() {
        return "PoliticalOffice{" +
                "name='" + name + '\'' +
                ", divisionId='" + divisionId + '\'' +
                ", levels=" + Arrays.toString(levels) +
                ", roles=" + Arrays.toString(roles) +
                ", officialIndices=" + Arrays.toString(officialIndices) +
                '}';
    }
}
