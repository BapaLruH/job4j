package ru.job4j.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.StringJoiner;

public class City {
    @JsonProperty("cityId")
    private int id;
    @JsonProperty("cityName")
    private String name;
    @JsonProperty("country")
    private Country country;

    public City() {
    }

    public City(int id) {
        this.id = id;
    }

    public City(String name, Country country) {
        this.name = name;
        this.country = country;
    }

    public City(int id, String name, Country country) {
        this(id);
        this.name = name;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        City city = (City) o;
        return id == city.id
                && Objects.equals(name, city.name)
                && Objects.equals(country, city.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, country);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", City.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("country=" + country)
                .toString();
    }
}
