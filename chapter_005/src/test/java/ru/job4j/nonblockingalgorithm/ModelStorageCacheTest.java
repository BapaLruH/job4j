package ru.job4j.nonblockingalgorithm;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ModelStorageCacheTest {
    @Test
    public void whenAddTwoDifferentModelsThenVersion1() throws InterruptedException {
        ModelStorageCache cache = new ModelStorageCache();
        Thread firstThread = new Thread(() -> cache.add(new Base(1, "task_1")));
        Thread secondThread = new Thread(() -> cache.add(new Base(2, "task_2")));
        firstThread.start();
        secondThread.start();
        firstThread.join();
        secondThread.join();
        assertThat(cache.get(1).getVersion(), is(1));
        assertThat(cache.get(2).getVersion(), is(1));
    }

    @Test
    public void whenFirstThreadAddsTwoModelsSecondThirdThreadChangesSecondModel() throws Exception {
        ModelStorageCache cache = new ModelStorageCache();
        AtomicReference<OptimisticException> optEx = new AtomicReference<>();
        Thread firstThread = new Thread(() -> {
            cache.add(new Base(1, "task_1"));
            cache.add(new Base(2, "task_2"));
            cache.add(new Base(3, "task_3"));
        });
        firstThread.start();
        firstThread.join();
        Thread secondThread = new Thread(() -> {
            try {
                cache.update(new Base(2, "Task2_1"));
            } catch (OptimisticException e) {
                optEx.set(e);
            }

        });
        Thread thirdThread = new Thread(() -> {
            try {
                cache.update(new Base(2, "Task2_2"));
            } catch (OptimisticException e) {
                optEx.set(e);
            }
        });
        secondThread.start();
        thirdThread.start();
        secondThread.join();
        thirdThread.join();
        assertThat(optEx.get().getMessage(), is("oops, something went wrong!"));
    }
}