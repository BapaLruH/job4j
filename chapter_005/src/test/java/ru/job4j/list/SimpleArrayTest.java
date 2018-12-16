package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {

    private SimpleArray<Integer> array;

    @Before
    public void setUp() {
        this.array = new SimpleArray<>();
        this.array.add(1);
    }

    @Test
    public void whenAddNewElementThenArrayContainsElement() {
        this.array.add(2);
        assertThat(this.array.get(1), is(2));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenIteratorHasOldSize() {
        Iterator<Integer> itr = this.array.iterator();
        this.array.add(2);
        itr.hasNext();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenArrayIsEmpty() {
        this.array.get(2);
    }

}