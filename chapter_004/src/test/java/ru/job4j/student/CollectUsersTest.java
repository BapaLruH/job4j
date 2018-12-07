package ru.job4j.student;

import org.junit.Test;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CollectUsersTest {
    @Test
    public void whenUsersSortedAndSelectedByScopeThenList() {
        Random rm = new Random();
        List<Student> students = List.of("Ivan", "Fedor", "Igor")
                .stream()
                .map(v -> new Student(v, rm.nextInt(5)))
                .collect(Collectors.toList());
        List<Student> result = new CollectUsers().levelOf(students, 2);
        assertThat(result.size(), is((int) students.stream().filter(student -> student.getScope() > 2).count()));
    }
}