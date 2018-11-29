package ru.job4j.search;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class PriorityQueueTest {

    @Test
    public void whenHigherPriority() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("low", 5));
        queue.put(new Task("high", 1));
        queue.put(new Task("pre-high", 2));
        queue.put(new Task("middle", 3));
        queue.put(new Task("pre-middle", 4));
        Task result = queue.take();
        assertThat(result.getDesc(), is("high"));
    }
}