package ru.job4j.generics;

/**
 * Class Base.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 14.12.2018
 */
abstract class Base {
    private final String id;

    Base(String id) {
        this.id = id;
    }

    /**
     * Returns the id at the specified element.
     *
     * @return the id at the specified element
     */
    String getId() {
        return id;
    }
}
