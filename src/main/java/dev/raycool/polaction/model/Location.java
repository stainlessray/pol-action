package dev.raycool.polaction.model;

import dev.raycool.polaction.officesresponse.NormalizedInput;

import java.util.ArrayList;
import java.util.List;

public class Location {
    private NormalizedInput searchLocation;
    private int countOfOffices;
    private int countOfOfficials;
    private static List<PublicOffice> publicOffices = new ArrayList<>();


    public Location() {
    }

    public Location(NormalizedInput searchLocation, List<PublicOffice> publicOffices) {
        this.searchLocation = searchLocation;
        this.publicOffices = publicOffices;
    }

    public void clearAll() {
        searchLocation = null;
        countOfOffices = 0;
        countOfOfficials = 0;
        publicOffices.clear();
    }

    public Integer getCountOfOffices() {
        return countOfOffices;
    }

    public void setCountOfOffices(Integer countOfOffices) {
        this.countOfOffices = countOfOffices;
    }

    public Integer getCountOfOfficials() {
        return countOfOfficials;
    }

    public void setCountOfOfficials(Integer countOfOfficials) {
        this.countOfOfficials = countOfOfficials;
    }

    public List<PublicOffice> getPublicOffices() {
        return this.publicOffices;
    }

    public void setPublicOffices(List<PublicOffice> publicOffices) {
        this.publicOffices = publicOffices;
    }

    public NormalizedInput getSearchLocation() {
        return searchLocation;
    }

    public void setSearchLocation(NormalizedInput searchLocation) {
        this.searchLocation = searchLocation;
    }

    public List<PublicOffice> getOffices() {
        return publicOffices;
    }

    public void setOffices(List<PublicOffice> publicOfficeMembers) {
        this.publicOffices = publicOfficeMembers;
    }

    public void addOffice(PublicOffice publicOffice) {
        this.publicOffices.add(publicOffice);
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
