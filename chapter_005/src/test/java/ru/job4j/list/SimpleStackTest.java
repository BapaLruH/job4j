package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.EmptyStackException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleStackTest {

    private SimpleStack<Integer> stack;

    @Before
    public void setUp() {
        this.stack = new SimpleStack<>();
    }

    @Test
    public void whenPushThreeElementThenSize3() {
        this.stack.push(1);
        this.stack.push(2);
        this.stack.push(3);
        assertThat(this.stack.size(), is(3));
    }

    @Test
    public void whenPushOneElementAndPollThenSize0AndThisElementReturned() {
        this.stack.push(1);
        Integer result = this.stack.poll();
        assertThat(result, is(1));
        assertThat(this.stack.isEmpty(), is(true));
    }

    @Test(expected = EmptyStackException.class)
    public void whenStackIsEmpty() {
        this.stack.poll();
    }
}