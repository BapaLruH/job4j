package ru.job4j.threads;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleThreadSafeListTest {

    private SimpleThreadSafeList<Integer> list;
    private Iterator iterator;

    @Before
    public void setUp() {
        this.list = new SimpleThreadSafeList<>();
        this.list.add(1);
        this.list.add(2);
        this.list.add(3);
    }

    @Test
    public void whenFirstThreadAndSecondThreadPutValuesThenIteratorContainsThisValues() throws InterruptedException {
        Thread threadFirst = new Thread(() -> {
            for (int i = 4; i < 7; i++) {
                list.add(i);
            }
        });
        Thread threadSecond = new Thread(() -> {
            for (int i = 7; i < 10; i++) {
                list.add(i);
            }
        });

        threadFirst.run();
        threadSecond.run();

        threadSecond.join();
        threadFirst.join();

        int i = 1;
        for (int value : list) {
            assertThat(value, is(i++));
        }
    }

    @Test
    public void whenFirstThreadGetIteratorBeforeNextThreadInputItems() throws InterruptedException {
        Thread threadFirst = new Thread(() -> iterator = list.iterator());

        Thread threadNext = new Thread(() -> {
            for (int i = 4; i < 7; i++) {
                list.add(i);
            }
        });

        threadFirst.start();
        threadFirst.join();
        threadNext.start();
        threadNext.join();

        assertThat(iterator.next(), is(1));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.hasNext(), is(false));
    }
}