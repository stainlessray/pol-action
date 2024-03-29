package dev.raycool.polaction.officialsresponsemodels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Arrays;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PoliticalOfficial {

    private String name;
    private String party;
    private String photoUrl;
    private Address[] addresses;
    private Phone[] phones;
    private Email[] emails;
    private Channel[] channels;
    private Url[] urls;

    public PoliticalOfficial() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address[] getAddress() {
        return addresses;
    }

    public void setAddress(Address[] address) {
        this.addresses = address;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public Phone[] getPhones() {
        return phones;
    }

    public void setPhones(Phone[] phones) {
        this.phones = phones;
    }

    public Url[] getUrls() {
        return urls;
    }

    public void setUrls(Url[] urls) {
        this.urls = urls;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Channel[] getChannels() {
        return channels;
    }

    public void setChannels(Channel[] channels) {
        this.channels = channels;
    }

    public Email[] getEmails() {
        return emails;
    }

    public void setEmails(Email[] emails) {
        this.emails = emails;
    }

    public String getPhonesToString() {
        String phoneList = Arrays.toString(phones)
                .replaceAll("[\\[\\]]", "");
        return phoneList;
    }

    @Override
    public String toString() {
        return
                name +
                Arrays.toString(addresses) +
                party +
                Arrays.toString(phones).replaceAll("[\\[\\]]", "") +
                Arrays.toString(urls) +
                photoUrl +
                Arrays.toString(channels) +
                 Arrays.toString(emails);
    }
}

