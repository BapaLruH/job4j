package ru.job4j.convert;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SortUserTest {

    @Test
    public void whenListContainsThreeUsers() {
        User ivan = new User("Ivan", 29);
        User serj = new User("Serj", 27);
        User oleg = new User("Oleg", 32);
        List<User> input = List.of(
                ivan, serj, oleg
        );
        Set<User> sorted = new SortUser().sort(input);
        assertThat(sorted.iterator().next(), is(serj));
    }

    @Test
    public void whenListSortedByNameLength() {
        User serj = new User("Sergej", 25);
        User ivan = new User("Ivan", 30);
        User sergej = new User("Sergej", 20);
        User fedor = new User("Fedor", 25);
        List<User> input = Arrays.asList(
                serj, ivan, sergej, fedor
        );
        List<User> expect = List.of(ivan, fedor, serj, sergej);
        List<User> result = new SortUser().sortNameLength(input);
        assertThat(result, is(expect));
    }

    @Test
    public void whenListSortedByAllFields() {
        User serj = new User("Sergej", 25);
        User ivan = new User("Ivan", 30);
        User sergej = new User("Sergej", 20);
        User fedor = new User("Fedor", 25);
        List<User> input = Arrays.asList(
                serj, ivan, sergej, fedor
        );
        List<User> expect = List.of(fedor, ivan, sergej, serj);
        List<User> result = new SortUser().sortByAllFields(input);
        assertThat(result, is(expect));
    }
}