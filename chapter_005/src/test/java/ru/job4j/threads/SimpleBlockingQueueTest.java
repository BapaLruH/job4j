package ru.job4j.threads;

import org.junit.Test;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleBlockingQueueTest {

    @Test
    public void whenFetchAllElementsThenGetIt() throws InterruptedException {
        SimpleBlockingQueue<Integer> simpleBlockingQueue = new SimpleBlockingQueue<>(100);
        CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        Thread producer = new Thread(() -> IntStream.range(1, 4).forEach(simpleBlockingQueue::offer));
        Thread consumer = new Thread(() -> {
            while (!simpleBlockingQueue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                try {
                    buffer.add(simpleBlockingQueue.poll());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        });
        producer.start();
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        assertThat(buffer, is(List.of(1, 2, 3)));
    }

    @Test
    public void whenAddFiveElementsInQueueThenContainsIt() throws InterruptedException {
        SimpleBlockingQueue<Integer> simpleBlockingQueue = new SimpleBlockingQueue<>(10);
        Thread producer = new Thread(() -> IntStream.range(0, 3).forEach(simpleBlockingQueue::offer));
        Thread producerNext = new Thread(() -> IntStream.range(3, 5).forEach(simpleBlockingQueue::offer));
        producer.start();
        producerNext.start();
        producer.join();
        producerNext.join();
        assertThat(simpleBlockingQueue.size(), is(5));
    }
}