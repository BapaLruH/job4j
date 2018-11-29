package ru.job4j.tracker;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Сlass Tracker.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 21.11.2018
 */
public class Tracker {
    private final List<Item> items = new ArrayList<>();
    private static Random rm = new Random();

    /**
     * Method add.
     * Adds a item to the array of items.
     *
     * @param item type Item.
     * @return item type Item.
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items.add(item);
        return item;
    }

    /**
     * Method generateId.
     * Generates id for the new item.
     *
     * @return id type String.
     */
    private String generateId() {
        return String.valueOf(rm.nextLong());
    }

    /**
     * Method replace.
     * Replaces an array item with an id.
     *
     * @param id   type String.
     * @param item type Item.
     * @return changes type boolean.
     */
    public boolean replace(String id, Item item) {
        Item itemForReplace = this.items.stream().filter(v -> v.getId().equals(id)).findFirst().orElse(null);
        if (itemForReplace != null) {
            item.setId(id);
            this.items.set(this.items.indexOf(itemForReplace), item);
        }
        return itemForReplace != null;
    }

    /**
     * Method delete.
     * Deleted an array item with an id.
     *
     * @param id type String.
     * @return changes type boolean.
     */
    public boolean delete(String id) {
        Item remove = this.items.stream()
                .filter(v -> v.getId().equals(id))
                .findFirst().orElse(null);
        this.items.remove(remove);
        return remove != null;
    }

    /**
     * Method findAll.
     * Returns all item of the array.
     *
     * @return items type List<Item>.
     */
    public List<Item> findAll() {
        return this.items;
    }

    /**
     * Method findByName.
     * Returns all items of the array with the name(key).
     *
     * @param key type String.
     * @return items type List<Item>.
     */
    public List<Item> findByName(String key) {
        return this.items.stream()
                .filter(v -> v.getName().equals(key))
                .collect(Collectors.toList());
    }

    /**
     * Method findById.
     * Returns item of the array with the id.
     *
     * @param id type String.
     * @return item type Item.
     */
    public Item findById(String id) {
        return this.items.stream()
                .filter(v -> v.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
