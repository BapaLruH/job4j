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
        List<User> input = Arrays.asList(
                ivan, serj, oleg
        );
        Set<User> sorted = new SortUser().sort(input);
        assertThat(sorted.iterator().next(), is(serj));
    }
}