package dev.raycool.polaction.model;

import dev.raycool.polaction.officesresponse.Level;
import dev.raycool.polaction.officesresponse.Role;
import dev.raycool.polaction.officialsresponse.PoliticalOfficial;

import java.util.ArrayList;
import java.util.List;

public class PublicOffice {
    private String officeName;
    private int countInThisOffice;
    private List<Level> levels = new ArrayList<>();
    private List<Role> roles = new ArrayList<>();
    private List<PoliticalOfficial> officials = new ArrayList<>();


    public PublicOffice() {
    }

    public PublicOffice(String officeName, List<Level> levels, List<Role> roles, List<PoliticalOfficial> officials) {
        this.officeName = officeName;
        this.levels = levels;
        this.roles = roles;
        this.officials = officials;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public Integer getCountInThisOffice() {
        return countInThisOffice;
    }

    public void setCountInThisOffice(Integer countInThisOffice) {
        this.countInThisOffice = countInThisOffice;
    }

    public List<Level> getLevels() {
        return levels;
    }

    public void setLevels(List<Level> levels) {
        this.levels = levels;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<PoliticalOfficial> getOfficials() {
        return officials;
    }

    public void setOfficials(List<PoliticalOfficial> officials) {
        this.officials = officials;
    }

    public void addLevel(Level level) {
        levels.add(level);
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public void addOfficial(PoliticalOfficial official) {
        officials.add(official);
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
