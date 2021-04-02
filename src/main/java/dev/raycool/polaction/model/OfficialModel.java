package dev.raycool.polaction.model;

import dev.raycool.polaction.officialsresponsemodels.PoliticalOfficial;

import java.util.List;
import java.util.Objects;

public class OfficialModel {

    private String officeName;
    private List<PoliticalOfficial> listOfOfficials;

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public List<PoliticalOfficial> getListOfOfficials() {
        return listOfOfficials;
    }

    public void setListOfOfficials(List<PoliticalOfficial> listOfOfficials) {
        this.listOfOfficials = listOfOfficials;
    }

    @Override
    public String toString() {
        return "OfficialModel{" +
                "officeName='" + officeName + '\'' +
                ", listOfOfficials=" + listOfOfficials +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OfficialModel that = (OfficialModel) o;
        return officeName.equals(that.officeName) && listOfOfficials.equals(that.listOfOfficials);
    }

    @Override
    public int hashCode() {
        return Objects.hash(officeName, listOfOfficials);
    }
}
