package ru.job4j.tracker;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * Сlass Item.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 21.11.2018
 */
public class Item {
    private String id;
    private String name;
    private String description;
    private long created;
    private String comments;
    private int authorId;
    private int stateId;
    private int categoryId;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
        this.created = System.currentTimeMillis();
    }

    public Item(String name, String description, long created) {
        this.name = name;
        this.description = description;
        this.created = created;
    }

    public Item(String id, String name, String description, int authorId, int stateId, int categoryId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.authorId = authorId;
        this.stateId = stateId;
        this.categoryId = categoryId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public long getCreated() {
        return created;
    }

    public String getComments() {
        return comments;
    }

    public int getAuthorId() {
        return this.authorId;
    }

    public int getStateId() {
        return this.stateId;
    }

    public int getCategoryId() {
        return this.categoryId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return authorId == item.authorId
                && stateId == item.stateId
                && categoryId == item.categoryId
                && Objects.equals(id, item.id)
                && Objects.equals(name, item.name)
                && Objects.equals(description, item.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, authorId, stateId, categoryId);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Item.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("name='" + name + "'")
                .add("description='" + description + "'")
                .add("created=" + created)
                .add("comments='" + comments + "'")
                .add("authorId=" + authorId)
                .add("stateId=" + stateId)
                .add("categoryId=" + categoryId)
                .toString();
    }
}
