package ru.job4j.convert;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserConvertTest {

    @Test
    public void whenListContainsTwoUsersThenMapSize2() {
        User ivan = new User("Ivan");
        User fedor = new User("Fedor");
        List<User> users = List.of(ivan, fedor);
        UserConvert convert = new UserConvert();
        HashMap<Integer, String> result = convert.process(users);
        HashMap<Integer, String> expect = new HashMap<>();
        expect.put(ivan.getId(), ivan.getName());
        expect.put(fedor.getId(), fedor.getName());
        assertThat(result, is(expect));
    }
}