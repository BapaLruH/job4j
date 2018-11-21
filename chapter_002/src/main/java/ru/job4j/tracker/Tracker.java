package ru.job4j.tracker;

import java.util.Arrays;
import java.util.Random;

/**
 * Сlass Tracker.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 21.11.2018
 */
public class Tracker {
    private final Item[] items = new Item[100];
    private int position = 0;
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
        this.items[this.position++] = item;
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
     */
    public void replace(String id, Item item) {
        for (int i = 0; i < this.position; i++) {
            if (items[i].getId().equals(id)) {
                item.setId(id);
                items[i] = item;
            }
        }
    }

    /**
     * Method delete.
     * Deleted an array item with an id.
     *
     * @param id type String.
     */
    public void delete(String id) {
        for (int i = 0; i < this.position; i++) {
            if (items[i].getId().equals(id)) {
                System.arraycopy(items, i + 1, items, i, this.items.length - i - 1);
                this.position--;
                break;
            }
        }
    }

    /**
     * Method findAll.
     * Returns all item of the array.
     *
     * @return items type Item[].
     */
    public Item[] findAll() {
        return Arrays.copyOf(items, position);
    }

    /**
     * Method findByName.
     * Returns all items of the array with the name(key).
     *
     * @param key type String.
     * @return items type Item[].
     */
    public Item[] findByName(String key) {
        Item[] result = new Item[this.position];
        int index = 0;
        for (int i = 0; i < this.position; i++) {
            if (items[i] != null && items[i].getName().equals(key)) {
                result[index++] = items[i];
            }
        }
        return Arrays.copyOf(result, index);
    }

    /**
     * Method findById.
     * Returns item of the array with the id.
     *
     * @param id type String.
     * @return item type Item.
     */
    public Item findById(String id) {
        Item result = null;
        for (Item item : items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }
}
