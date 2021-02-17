package dev.raycool.polaction.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Channel {
    private String type;
    private String id;

    public Channel() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "type='" + type + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
