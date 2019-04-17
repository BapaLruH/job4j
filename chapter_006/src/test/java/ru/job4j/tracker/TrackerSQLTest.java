package ru.job4j.tracker;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TrackerSQLTest {
    private TrackerSQL trackerSQL;
    private Item item;

    @Test
    public void initTest() {
        this.trackerSQL = new TrackerSQL();
        assertThat(this.trackerSQL.init(), is(true));
    }
    /* local tests
    @Before
    public void setUp() {
        this.trackerSQL = new TrackerSQL();
        this.item = new Item("Text", "description");
        this.item.setAuthorId(1);
        this.item.setStateId(1);
        this.item.setCategoryId(1);
        this.trackerSQL.add(this.item);
    }

    @After
    public void setDown() {
        this.trackerSQL.delete(this.item.getId());
    }

    @Test
    public void checkConnection() {
        assertThat(this.trackerSQL.init(), is(true));
    }

    @Test
    public void whenAddedNewItemThenTrackerContainsIt() {
        String id = this.item.getId();
        assertThat(this.trackerSQL.findById(id), is(this.item));
    }

    @Test
    public void whenUpdateItem() {
        this.item.setName("before update");
        String id = this.item.getId();
        this.trackerSQL.replace(id, this.item);
        assertThat(this.trackerSQL.findByName("before update"), is(List.of(item)));
    }

    @Test
    public void whenRemovedItem() {
        Item item = new Item("for remove", "description_1");
        item.setAuthorId(1);
        item.setStateId(1);
        item.setCategoryId(1);
        this.trackerSQL.add(item);
        List<Item> items = this.trackerSQL.findByName("for remove");
        String id = null;
        if (items.size() > 0) {
            id = items.get(0).getId();
            this.trackerSQL.delete(id);
        }
        assertThat(null, is(this.trackerSQL.findById(id)));
    }

    @Test
    public void whenGetAllItems() {
        List<Item> items = this.trackerSQL.findAll();
        assertThat(items.size(), is(4));
    }*/
}