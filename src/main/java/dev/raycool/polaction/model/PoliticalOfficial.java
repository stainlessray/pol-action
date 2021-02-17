package dev.raycool.polaction.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PoliticalOfficial {

    private String name;
    private Address[] address;
    private String party;
    private Phone phone;
    private Url URL;
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

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Url getURL() {
        return URL;
    }

    public void setURL(Url URL) {
        this.URL = URL;
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
                ", address=" + address +
                ", party='" + party + '\'' +
                ", phone=" + phone +
                ", URL=" + URL +
                ", photoUrl='" + photoUrl + '\'' +
                ", channel=" + channel +
                '}';
    }
}
