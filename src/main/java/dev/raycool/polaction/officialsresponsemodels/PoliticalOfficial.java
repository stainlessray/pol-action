package dev.raycool.polaction.officialsresponsemodels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Arrays;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PoliticalOfficial {

    private String name;
    private Address[] address;
    private String party;
    private Phone[] phones;
    private Email[] emails;
    private String photoUrl;
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
        return address;
    }

    public void setAddress(Address[] address) {
        this.address = address;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public Phone[] getPhones() { return phones; }

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

    @Override
    public String toString() {
        return "PoliticalOfficial{" +
                "name='" + name + '\'' +
                ", address=" + Arrays.toString(address) +
                ", party='" + party + '\'' +
                ", phones=" + Arrays.toString(phones) +
                ", urls=" + Arrays.toString(urls) +
                ", photoUrl='" + photoUrl + '\'' +
                ", channels=" + Arrays.toString(channels) +
                ", emails=" + Arrays.toString(emails) +
                '}';
    }
}
