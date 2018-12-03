package ru.job4j.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserConvertTest {
    @Test
    public void whenListOfStringsConvertToListOfUsers() {
        List<String> names = Arrays.asList("Petr", "Nick", "Ban");
        UserConvert users = new UserConvert();
        List<UserConvert.User> data = users.convert(names, UserConvert.User::new);
        assertThat(data.get(0).toString(), is("User[name='Petr']"));
    }
}