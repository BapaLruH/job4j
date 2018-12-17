package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleListTest {

    private SimpleList<Integer> list;

    @Before
    public void setUp() {
        this.list = new SimpleList<>();
        this.list.add(1);
        this.list.add(2);
        this.list.add(3);
    }

    @Test
    public void whenGetElementWithIndex1ThenResult2() {
        assertThat(this.list.get(1), is(2));
    }

    @Test
    public void whenGetFirstAndLastValueThenResult1And3() {
        assertThat(this.list.getFirst(), is(1));
        assertThat(this.list.getLast(), is(3));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenIndexNotExist() {
        this.list.get(100);
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenIteratorHasOldSize() {
        Iterator<Integer> itr = this.list.iterator();
        this.list.add(4);
        itr.hasNext();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNotHasNext() {
        Iterator<Integer> itr = this.list.iterator();
        itr.next();
        itr.next();
        itr.next();
        itr.next();
    }
}