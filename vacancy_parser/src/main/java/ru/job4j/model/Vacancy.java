package ru.job4j.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Сlass Vacancy.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 28.04.2019
 */
public class Vacancy {
    private int id;
    private String author;
    private String name;
    private String text;
    private String link;
    private String source;
    private LocalDateTime date;

    public Vacancy(int id, String author, String name, String text, String link, String source, LocalDateTime date) {
        this.id = id;
        this.author = author;
        this.name = name;
        this.text = text;
        this.link = link;
        this.source = source;
        this.date = date;
    }

    public Vacancy(String author, String name, String text, String link, String source, LocalDateTime date) {
        this.author = author;
        this.name = name;
        this.text = text;
        this.link = link;
        this.source = source;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vacancy vacancy = (Vacancy) o;
        return Objects.equals(author, vacancy.author)
                && Objects.equals(name, vacancy.name)
                && Objects.equals(link, vacancy.link)
                && Objects.equals(source, vacancy.source)
                && Objects.equals(date, vacancy.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, name, link, source, date);
    }
}
