package ru.job4j.generics;

/**
 * Interface Store.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 14.12.2018
 */
public interface Store<T extends Base> {
    /**
     * Ensures that this array contains the specific element.
     *
     * @param model element whose presence in this array is to be ensured
     */
    void add(T model);

    /**
     * Inserts the specified element at the specified position in the array.
     *
     * @param id    id of the element to replace
     * @param model element whose presence in this array is to be ensured
     * @return {@code true} if this array changed as a result of the call
     */
    boolean replace(String id, T model);

    /**
     * Removes the element at the specified position in the array.
     *
     * @param id id of the element to delete
     * @return {@code true} if this array changed as a result of the call
     */
    boolean delete(String id);

    /**
     * Returns the element at the specified id in the array.
     *
     * @param id id of the element to search
     * @return the element at the specified id
     */
    T findById(String id);
}
