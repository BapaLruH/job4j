package ru.job4j.set;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleSetTest {

    SimpleSet<Integer> set;

    @Before
    public void setUp() {
        this.set = new SimpleSet<>();
        this.set.add(1);
        this.set.add(2);
        this.set.add(3);
    }

    @Test
    public void whenAddNewElementAndSetContainsSimilarElement() {
        this.set.add(1);
        assertThat(this.set.size(), is(3));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenGetIteratorAndAddNewElementInSetThenException() {
        Iterator<Integer> itr = this.set.iterator();
        this.set.add(4);
        itr.hasNext();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenUseNextMoreThenExpected() {
        Iterator<Integer> itr = this.set.iterator();
        itr.next();
        itr.next();
        itr.next();
        itr.next();
    }
}