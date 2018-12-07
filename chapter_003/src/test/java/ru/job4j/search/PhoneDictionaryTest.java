package ru.job4j.search;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PhoneDictionaryTest {

    @Test
    public void whenFindByNameThenResult() {
        var first = new Person("Evgeny", "Shevchenko", "5656432", "Rostov-on-Don");
        var phones = new PhoneDictionary(first);
        List<Person> persons = phones.find("en");
        assertThat(persons.iterator().next().getSurname(), is("Shevchenko"));
    }

    @Test
    public void whenFindByNameThenResultListOfTwoPersons() {
        var first = new Person("Evgeny", "Shevchenko", "5656432", "Rostov-on-Don");
        var second = new Person("Ivan", "Ivanenko", "23432444", "Rostov-on-Don");
        var third = new Person("Fedor", "Morozov", "97438328", "Rostov-on-Don");
        var phones = new PhoneDictionary(first);
        phones.addNewPersonInList(second);
        phones.addNewPersonInList(third);
        List<Person> persons = phones.find("enko");
        List<Person> expect = List.of(first, second);
        assertThat(persons, is(expect));
    }

    @Test
    public void whenFindByDifferentNameThenSimilarResult() {
        var first = new Person("Evgeny", "Shevchenko", "5656444", "Rostov-on-Don");
        var second = new Person("Ivan", "Ivanenko", "23432444", "Rostov-on-Don");
        var third = new Person("Fedor", "Morozov", "97438328", "Rostov-on-Don");
        var phones = new PhoneDictionary(first);
        phones.addNewPersonInList(second);
        phones.addNewPersonInList(third);
        List<Person> firstRequest = phones.find("enko");
        List<Person> secondRequest = phones.find("444");
        assertThat(firstRequest, is(secondRequest));
    }
}