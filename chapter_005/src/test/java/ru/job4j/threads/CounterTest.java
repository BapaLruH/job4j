package ru.job4j.threads;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CounterTest {
    private class ThreadCounter extends Thread {
        private final Counter counter;

        private ThreadCounter(final Counter counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            this.counter.increment();
        }
    }

    @Test
    public void whenExecute2ThreadThen2() throws InterruptedException {
        final Counter counter = new Counter();
        Thread first = new ThreadCounter(counter);
        Thread second = new ThreadCounter(counter);
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(counter.getCount(), is(2));
    }
}