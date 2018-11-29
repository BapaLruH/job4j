package ru.job4j.search;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PhoneDictionaryTest {

    @Test
    public void whenFindByNameThenResult() {
        Person first = new Person("Evgeny", "Shevchenko", "5656432", "Rostov-on-Don");
        PhoneDictionary phones = new PhoneDictionary(first);
        List<Person> persons = phones.find("en");
        assertThat(persons.iterator().next().getSurname(), is("Shevchenko"));
    }

    @Test
    public void whenFindByNameThenResultListOfTwoPersons() {
        Person first = new Person("Evgeny", "Shevchenko", "5656432", "Rostov-on-Don");
        Person second = new Person("Ivan", "Ivanenko", "23432444", "Rostov-on-Don");
        Person third = new Person("Fedor", "Morozov", "97438328", "Rostov-on-Don");
        PhoneDictionary phones = new PhoneDictionary(first);
        phones.addNewPersonInList(second);
        phones.addNewPersonInList(third);
        List<Person> persons = phones.find("enko");
        List<Person> expect = new ArrayList<>();
        expect.add(first);
        expect.add(second);
        assertThat(persons, is(expect));
    }

    @Test
    public void whenFindByDifferentNameThenSimilarResult() {
        Person first = new Person("Evgeny", "Shevchenko", "5656444", "Rostov-on-Don");
        Person second = new Person("Ivan", "Ivanenko", "23432444", "Rostov-on-Don");
        Person third = new Person("Fedor", "Morozov", "97438328", "Rostov-on-Don");
        PhoneDictionary phones = new PhoneDictionary(first);
        phones.addNewPersonInList(second);
        phones.addNewPersonInList(third);
        List<Person> firstRequest = phones.find("enko");
        List<Person> secondRequest = phones.find("444");
        assertThat(firstRequest, is(secondRequest));
    }
}