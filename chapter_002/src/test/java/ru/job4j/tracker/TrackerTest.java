package ru.job4j.tracker;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class TrackerTest {

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test", "new description", 111L);
        tracker.add(item);
        assertThat(tracker.findAll().get(0), is(item));
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
    public void whenTrackerContainsItemWithNameThenInputItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("First test name", "first description", 123L);
        Item item1 = new Item("First test name", "first description", 123L);
        Item item2 = new Item("First test name", "first description", 123L);
        List<Item> expect = Arrays.asList(item, item1, item2);
        tracker.add(item);
        tracker.add(item1);
        tracker.add(item2);
        assertThat(tracker.findByName("First test name"), is(expect));
    }

    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "test name", "description", "y"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll().get(0).getName(), is("test name"));
    }

    @Test
    public void whenUpdateThenTrackerHasUpdateValue() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Test name", "description"));
        Input input = new StubInput(new String[]{"2", item.getId(), "new name", "updated", "y"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()).getName(), is("new name"));
    }

    @Test
    public void whenDeleteThenTrackerNotHasValue() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Test name", "description"));
        Input input = new StubInput(new String[]{"3", item.getId(), "y"});
        new StartUI(input, tracker).init();
        assertNull(tracker.findById(item.getId()));
    }

    @Test
    public void whenFindItemByIdThenReturnValue() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Test name", "description"));
        Input input = new StubInput(new String[]{"4", item.getId(), "y"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()).getName(), is("Test name"));
    }

    @Test
    public void whenFindItemByNameThenReturnValue() {
        Tracker tracker = new Tracker();
        Item first = tracker.add(new Item("Test name", "description"));
        Item second = tracker.add(new Item("Test name", "description_1"));
        Input input = new StubInput(new String[]{"5", "Test name", "y"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findByName("Test name"), is(Arrays.asList(first, second)));
    }
}