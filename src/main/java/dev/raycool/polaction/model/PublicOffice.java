package dev.raycool.polaction.model;

import dev.raycool.polaction.officesresponsemodels.Level;
import dev.raycool.polaction.officesresponsemodels.Role;
import dev.raycool.polaction.officialsresponsemodels.PoliticalOfficial;

import java.util.ArrayList;
import java.util.List;

public class PublicOffice {

    private String name;
    private List<Level> levels = new ArrayList<>();
    private List<Role> roles = new ArrayList<>();
    private List<PoliticalOfficial> officials = new ArrayList<>();


    public PublicOffice() {
    }

    public PublicOffice(String name, List<Level> levels, List<Role> roles, List<PoliticalOfficial> officials) {
        this.name = name;
        this.levels = levels;
        this.roles = roles;
        this.officials = officials;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "PublicOffice{" +
                "name='" + name + '\'' +
                ", levels=" + levels +
                ", roles=" + roles +
                ", officials=" + officials +
                '}';
    }
}
