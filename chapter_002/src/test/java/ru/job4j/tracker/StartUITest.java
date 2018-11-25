package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StartUITest {

    private final String menu = new StringJoiner(System.lineSeparator())
            .add("0. Add new item")
            .add("1. Show all items")
            .add("2. Edit item")
            .add("3. Delete item")
            .add("4. Find item by Id")
            .add("5. Find items by name")
            .toString();
    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(out));
    }

    @After
    public void backOutput() {
        System.setOut(stdout);
    }

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "test", "description", "y"});
        new StartUI(input, tracker).init();
        assertThat(new String(out.toByteArray()), is(
                new StringJoiner(System.lineSeparator())
                        .add(menu)
                        .add("------------ Добавление новой заявки --------------")
                        .add(String.format("------------ Новая заявка с getId : %s-----------", tracker.findAll()[0].getId()))
                        .add("")
                        .toString()
        ));
    }

    @Test
    public void whenUserChooseFindAllThenOutputsMenuElementsMenu() {
        Tracker tracker = new Tracker();
        Item item = new Item("First test name", "first description", 123L);
        Item item1 = new Item("First test name", "first description", 123L);
        Item item2 = new Item("First test name", "first description", 123L);
        tracker.add(item);
        tracker.add(item1);
        tracker.add(item2);
        Input input = new StubInput(new String[]{"1", "y"});
        new StartUI(input, tracker).init();
        assertThat(new String(out.toByteArray()), is(
                new StringJoiner(System.lineSeparator())
                        .add(menu)
                        .add(item.toString())
                        .add(item1.toString())
                        .add(item2.toString())
                        .add("")
                        .toString()
        ));
    }

    @Test
    public void whenUpdateThenPrintUpdateId() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Test name", "description"));
        Input input = new StubInput(new String[]{"2", item.getId(), "new name", "updated", "y"});
        new StartUI(input, tracker).init();
        assertThat(new String(out.toByteArray()), is(
                new StringJoiner(System.lineSeparator())
                        .add(menu)
                        .add("------------ Изменение заявки --------------")
                        .add(String.format("------------ Заявка с Id : %s изменена -----------", item.getId()))
                        .add("")
                        .toString()
        ));
    }

    @Test
    public void whenDeleteThenPrintDeletedId() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Test name", "description"));
        Input input = new StubInput(new String[]{"3", item.getId(), "y"});
        new StartUI(input, tracker).init();
        assertThat(new String(out.toByteArray()), is(
                new StringJoiner(System.lineSeparator())
                        .add(menu)
                        .add("------------ Удаление заявки --------------")
                        .add(String.format("------------ Заявка с Id : %s удалена -----------", item.getId()))
                        .add("")
                        .toString()
        ));
    }

    @Test
    public void whenFindItemByIdThenPrintValue() {
        Tracker tracker = new Tracker();
        Item first = tracker.add(new Item("Test name", "description"));
        Item second = tracker.add(new Item("Test name", "description"));
        Item third = tracker.add(new Item("Test name", "description"));
        Input input = new StubInput(new String[]{"4", second.getId(), "y"});
        new StartUI(input, tracker).init();
        assertThat(new String(out.toByteArray()), is(
                new StringJoiner(System.lineSeparator())
                        .add(menu)
                        .add("------------ Поиск заявки по Id --------------")
                        .add(second.toString())
                        .add("")
                        .toString()
        ));
    }

    @Test
    public void whenFindItemByNameThenPrintTwoValues() {
        Tracker tracker = new Tracker();
        Item first = tracker.add(new Item("Test name", "description"));
        Item second = tracker.add(new Item("Test name", "description_1"));
        Input input = new StubInput(new String[]{"5", "Test name", "y"});
        new StartUI(input, tracker).init();
        assertThat(new String(out.toByteArray()), is(
                new StringJoiner(System.lineSeparator())
                        .add(menu)
                        .add("------------ Поиск заявки по имени --------------")
                        .add(String.format("------------ Найденные заявки с именем : %s -----------", first.getName()))
                        .add(first.toString())
                        .add(second.toString())
                        .add("")
                        .toString()
        ));
    }
}
