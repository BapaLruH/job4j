package ru.job4j.search;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Сlass PhoneDictionary.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 29.11.2018
 */
public class PhoneDictionary {
    private List<Person> persons = new ArrayList<>();

    public PhoneDictionary(Person person) {
        this.persons.add(person);
    }

    /**
     * Method addNewPersonInList.
     * Adds a person to the list of persons.
     *
     * @param person type Person.
     */
    public void addNewPersonInList(Person person) {
        this.persons.add(person);
    }

    /**
     * Method find.
     * Returns all persons of the list with the name(key).
     *
     * @param key type String.
     * @return result type List<Person>.
     */
    public List<Person> find(String key) {
        return persons.stream()
                .filter(person -> person.toString().contains(key))
                .collect(Collectors.toList());
    }
}
