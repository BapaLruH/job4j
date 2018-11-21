package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class TrackerTest {

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test", "new description", 111L);
        tracker.add(item);
        assertThat(tracker.findAll()[0], is(item));
    }

    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("First test name", "first description", 123L);
        tracker.add(previous);
        Item next = new Item("Second test name", "second description", 1234L);
        tracker.replace(previous.getId(), next);
        assertThat(tracker.findById(previous.getId()).getName(), is("Second test name"));
    }

    @Test
    public void whenTrackerNotHasItemWithNameThenNull() {
        Tracker tracker = new Tracker();
        Item item = new Item("First test name", "first description", 123L);
        Item item1 = new Item("First test name", "first description", 123L);
        Item item2 = new Item("First test name", "first description", 123L);
        tracker.add(item);
        tracker.add(item1);
        tracker.add(item2);
        tracker.delete(item.getId());
        assertNull(tracker.findById(item.getId()));
    }

    @Test
    public void whenTrackerContainsItemWhithNameThenInputItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("First test name", "first description", 123L);
        Item item1 = new Item("First test name", "first description", 123L);
        Item item2 = new Item("First test name", "first description", 123L);
        Item[] expect = {item, item1, item2};
        tracker.add(item);
        tracker.add(item1);
        tracker.add(item2);
        assertThat(tracker.findByName("First test name"), is(expect));
    }
}