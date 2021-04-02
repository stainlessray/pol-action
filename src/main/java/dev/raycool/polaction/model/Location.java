package dev.raycool.polaction.model;

import java.util.ArrayList;
import java.util.List;

public class Location {

    private String locationData;
    private List<PublicOffice> publicOffices = new ArrayList<>();

    public Location() {
    }

    public Location(String locationData, List<PublicOffice> publicOffices) {
        this.locationData = locationData;
        this.publicOffices = publicOffices;
    }

    public String getLocationData() {
        return locationData;
    }

    public void setLocationData(String locationData) {
        this.locationData = locationData;
    }

    public List<PublicOffice> getOfficeModels() {
        return publicOffices;
    }

    public void setOfficeModels(List<PublicOffice> publicOfficeMembers) {
        this.publicOffices = publicOfficeMembers;
    }

    public void addOfficeModel(PublicOffice publicOffice) {
        this.publicOffices.add(publicOffice);
    }

    @Override
    public String toString() {
        return "Location{" +
                "locationData='" + locationData + '\'' +
                ", publicOfficeMembers=" + publicOffices +
                '}';
    }
}
