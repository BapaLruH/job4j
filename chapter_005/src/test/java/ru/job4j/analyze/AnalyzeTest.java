package ru.job4j.analyze;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AnalyzeTest {
    private Analyze analyze;
    private List<Analyze.User> previous;
    private List<Analyze.User> current;

    @Before
    public void setUp() {
        analyze = new Analyze();
        previous = List.of(
                new Analyze.User(1, "Ivan"),
                new Analyze.User(2, "Igor"),
                new Analyze.User(3, "Fedor"),
                new Analyze.User(4, "Mark")
        );
    }

    @Test
    public void whenOneAddedDeletedChanged() {
        current = new ArrayList<>(previous);
        Analyze.User usr = new Analyze.User(2, "Ignat");
        current.set(1, usr);
        current.remove(0);
        current.add(new Analyze.User(5, "Maxim"));
        Analyze.Info info = analyze.diff(previous, current);
        assertThat(info.added, is(1));
        assertThat(info.changed, is(1));
        assertThat(info.deleted, is(1));
    }

    @Test
    public void whenTwoAddedDeletedChanged() {
        current = new ArrayList<>(previous);
        Analyze.User first = new Analyze.User(2, "Ignat");
        Analyze.User second = new Analyze.User(3, "Artem");
        current.set(1, first);
        current.set(2, second);
        current.remove(0);
        current.remove(2);
        current.add(new Analyze.User(5, "Maxim"));
        current.add(new Analyze.User(6, "Max"));
        Analyze.Info info = analyze.diff(previous, current);
        assertThat(info.added, is(2));
        assertThat(info.changed, is(2));
        assertThat(info.deleted, is(2));
    }

    @Test
    public void whenTwoAddedOneDeletedTwoChanged() {
        current = new ArrayList<>(previous);
        Analyze.User first = new Analyze.User(2, "Ignat");
        Analyze.User second = new Analyze.User(3, "Artem");
        current.set(1, first);
        current.set(2, second);
        current.remove(0);
        current.add(new Analyze.User(5, "Maxim"));
        current.add(new Analyze.User(6, "Max"));
        Analyze.Info info = analyze.diff(previous, current);
        assertThat(info.added, is(2));
        assertThat(info.changed, is(2));
        assertThat(info.deleted, is(1));
    }

    @Test
    public void whenTwoAdded() {
        current = new ArrayList<>(previous);
        current.add(new Analyze.User(5, "Maxim"));
        current.add(new Analyze.User(6, "Max"));
        Analyze.Info info = analyze.diff(previous, current);
        assertThat(info.added, is(2));
        assertThat(info.changed, is(0));
        assertThat(info.deleted, is(0));
    }

    @Test
    public void whenTwoDeleted() {
        current = new ArrayList<>(previous);
        current.remove(0);
        current.remove(0);
        Analyze.Info info = analyze.diff(previous, current);
        assertThat(info.added, is(0));
        assertThat(info.changed, is(0));
        assertThat(info.deleted, is(2));
    }

    @Test
    public void whenTwoChanged() {
        current = new ArrayList<>(previous);
        Analyze.User first = new Analyze.User(2, "Ignat");
        Analyze.User second = new Analyze.User(3, "Artem");
        current.set(1, first);
        current.set(2, second);
        Analyze.Info info = analyze.diff(previous, current);
        assertThat(info.added, is(0));
        assertThat(info.changed, is(2));
        assertThat(info.deleted, is(0));
    }
}