package ru.job4j.nonblockingalgorithm;

public interface Cache {

    /**
     * Adds the specified element on this cache.
     *
     * @param model an element to add.
     */
    void add(Base model);

    /**
     * Returns the element at the specified id in the cache.
     *
     * @param id of the element to search.
     * @return the element at the specified id.
     */
    Base get(int id);

    /**
     * Updates an element in the cache.
     *
     * @param model an element to update.
     */
    void update(Base model);

    /**
     * Removes an element in the cache.
     *
     * @param model an element to remove.
     */
    void delete(Base model);
}
