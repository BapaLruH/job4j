package ru.job4j.map;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleMapTest {
    private SimpleMap<String, Integer> map;

    @Before
    public void setUp() {
        this.map = new SimpleMap<>();
        this.map.insert("Test_1", 1);
        this.map.insert("Test_2", 2);
        this.map.insert("Test_3", 3);
        this.map.insert("Test_4", 4);
    }

    @Test
    public void whenAddNewElementThenMapContainsIt() {
        boolean result = this.map.insert("Test_5", 5);
        assertTrue(result);
        assertThat(this.map.get("Test_5"), is(5));
    }

    @Test
    public void whenDeleteItemThenMapNotContainsIt() {
        boolean result = this.map.delete("Test_1");
        assertTrue(result);
        assertNull(this.map.get("Test_1"));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenIteratorHasOldSize() {
        Iterator<Integer> itr = this.map.iterator();
        this.map.insert("Test_5", 5);
        itr.hasNext();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNotHasNext() {
        Iterator<Integer> itr = this.map.iterator();
        itr.next();
        itr.next();
        itr.next();
        itr.next();
        itr.next();
    }

    @Test
    public void whenAddSeventeenElementsThenContainerSizeThirtyTwo() {
        for (int i = 5; i < 22; i++) {
            this.map.insert("Test_" + i, i);
        }
        try {
            Field field = this.map.getClass().getDeclaredField("values");
            field.setAccessible(true);
            int result = ((Object[]) field.get(this.map)).length;
            assertThat(result, is(32));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}