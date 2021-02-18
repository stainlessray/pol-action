package dev.raycool.polaction.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PoliticalOfficial {

    private String name;
    private Address[] address;
    private String party;
    private Phone[] phones;
    private Url[] urls;
    private String photoUrl;
    private Channel channel;
    private Email email;

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

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
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
                ", channel=" + channel +
                ", email=" + email +
                '}';
    }
}
