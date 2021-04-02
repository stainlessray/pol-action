package dev.raycool.polaction.officesresponsemodels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Level {
    private String country;

    public Level() {
    }

    public Level(String country) {
        this.country = country;
    }

    public String getLevel() {
        return country;
    }

    public void setLevel(String level) {
        this.country = level;
    }

    @Override
    public String toString() {
        return "Level{" +
                "country='" + country + '\'' +
                '}';
    }
}
