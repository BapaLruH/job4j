package ru.job4j.tracker;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TrackerSQLTest {
    private TrackerSQL trackerSQL;
    private Item item;

    public Connection init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Before
    public void initConnectionAndCreateItem() throws Exception {
        this.trackerSQL = new TrackerSQL(ConnectionRollback.create(this.init()));
        this.item = new Item("name", "desc");
        this.item.setAuthorId(1);
        this.item.setStateId(1);
        this.item.setCategoryId(1);
        this.trackerSQL.add(item);
        assertThat(this.trackerSQL.findByName("name").size(), is(1));
    }

    @After
    public void closeConnection() throws Exception {
        this.trackerSQL.close();
    }

    @Test
    public void initTest() {
        this.trackerSQL = new TrackerSQL();
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
    }
}