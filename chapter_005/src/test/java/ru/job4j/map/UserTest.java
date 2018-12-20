package ru.job4j.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;

public class UserTest {
    @Test
    public void whenNotOverrideEqualsAndHashCode() {
        User first = new User("Fedor", 1, new GregorianCalendar(25, Calendar.FEBRUARY, 1991));
        User second = new User("Fedor", 1, new GregorianCalendar(25, Calendar.FEBRUARY, 1991));
        Map<User, String> users = new HashMap<>();
        users.put(first, "first");
        users.put(second, "second");
        System.out.println(users);
        assertFalse(first.equals(second));
    }

    @Test
    public void whenOverrideEquals() {
        User first = new User("Fedor", 1, new GregorianCalendar(25, Calendar.FEBRUARY, 1991));
        User second = new User("Fedor", 1, new GregorianCalendar(25, Calendar.FEBRUARY, 1991));
        Map<User, String> users = new HashMap<>();
        users.put(first, "first");
        users.put(second, "second");
        System.out.println(users);
        assertFalse(first.equals(second));
    }
}