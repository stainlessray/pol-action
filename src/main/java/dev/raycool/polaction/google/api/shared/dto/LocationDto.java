package dev.raycool.polaction.google.api.shared.dto;

import dev.raycool.polaction.google.api.offices.model.response.NormalizedInput;

import java.util.ArrayList;
import java.util.List;

public class LocationDto {
    private NormalizedInput searchLocation;
    private int countOfOffices;
    private int countOfOfficials;
    private static List<PublicOfficeDto> publicOfficeDtos = new ArrayList<>();


    public LocationDto() {
    }

    public LocationDto(NormalizedInput searchLocation, List<PublicOfficeDto> publicOfficeDtos) {
        this.searchLocation = searchLocation;
        this.publicOfficeDtos = publicOfficeDtos;
    }

    public void clearAll() {
        searchLocation = null;
        countOfOffices = 0;
        countOfOfficials = 0;
        publicOfficeDtos.clear();
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

    public List<PublicOfficeDto> getPublicOffices() {
        return this.publicOfficeDtos;
    }

    public void setPublicOffices(List<PublicOfficeDto> publicOfficeDtos) {
        this.publicOfficeDtos = publicOfficeDtos;
    }

    public NormalizedInput getSearchLocation() {
        return searchLocation;
    }

    public void setSearchLocation(NormalizedInput searchLocation) {
        this.searchLocation = searchLocation;
    }

    public List<PublicOfficeDto> getOffices() {
        return publicOfficeDtos;
    }

    public void setOffices(List<PublicOfficeDto> publicOfficeDtoMembers) {
        this.publicOfficeDtos = publicOfficeDtoMembers;
    }

    public void addOffice(PublicOfficeDto publicOfficeDto) {
        this.publicOfficeDtos.add(publicOfficeDto);
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
