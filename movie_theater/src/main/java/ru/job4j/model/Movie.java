package ru.job4j.model;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * Сlass Movie.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 14.07.2019
 */
public class Movie {
    private int id;
    private String name;
    private String link;

    public Movie() {
    }

    public Movie(int id) {
        this.id = id;
    }

    public Movie(String name, String link) {
        this.name = name;
        this.link = link;
    }

    public Movie(int id, String name, String link) {
        this(name, link);
        this.id = id;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Movie movie = (Movie) o;
        return id == movie.id
                && Objects.equals(name, movie.name)
                && Objects.equals(link, movie.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, link);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Movie.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("link='" + link + "'")
                .toString();
    }
}
