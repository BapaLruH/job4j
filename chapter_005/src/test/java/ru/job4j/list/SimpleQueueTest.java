package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.EmptyStackException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleQueueTest {

    private SimpleQueue<Integer> queue;

    @Before
    public void setUp() {
        this.queue = new SimpleQueue<>();
        this.queue.push(1);
        this.queue.push(2);
        this.queue.push(3);
    }

    @Test
    public void whenPollFirstElementThenResultThisValue() {
        assertThat(this.queue.size(), is(3));
        assertThat(this.queue.poll(), is(1));
        assertThat(this.queue.size(), is(2));
    }

    @Test(expected = EmptyStackException.class)
    public void whenQueueIsEmptyAndPollNextElementThenException() {
        this.queue.poll();
        this.queue.poll();
        this.queue.poll();
        this.queue.poll();
    }

    @Test
    public void whenPushPollPushPoll() {
        this.queue.push(4);
        assertThat(this.queue.poll(), is(1));
        this.queue.push(5);
        assertThat(this.queue.poll(), is(2));
    }
}