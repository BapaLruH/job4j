package ru.job4j.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.StringJoiner;

public class Person {
    @JsonProperty("FirstName")
    private String firstName;
    @JsonProperty("LastName")
    private String lastName;
    @JsonProperty("Description")
    private String description;
    @JsonProperty("Gender")
    private String sex;

    public Person() {
    }

    public Person(String firstName, String lastName, String description, String sex) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.sex = sex;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        return Objects.equals(firstName, person.firstName)
                && Objects.equals(lastName, person.lastName)
                && Objects.equals(description, person.description)
                && Objects.equals(sex, person.sex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, description, sex);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Person.class.getSimpleName() + "[", "]")
                .add("firstName='" + firstName + "'")
                .add("lastName='" + lastName + "'")
                .add("description='" + description + "'")
                .add("sex='" + sex + "'")
                .toString();
    }
}
