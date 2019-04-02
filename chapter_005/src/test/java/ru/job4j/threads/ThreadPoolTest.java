package ru.job4j.threads;

import org.junit.Test;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ThreadPoolTest {

    @Test
    public void whenPoolAddTenValuesThenSize10() throws InterruptedException {
        ThreadPool threadPool = new ThreadPool();
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();
        Runnable task = (() -> list.add(1));
        for (int i = 0; i < 10; i++) {
            threadPool.work(task);
        }
        Thread.sleep(1000);
        threadPool.shutdown();
        Thread.sleep(1000);
        assertThat(list.size(), is(10));
    }

}