package ru.job4j.threads;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Class SimpleBlockingQueue.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 28.03.2019
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();
    private final int capacity;

    public SimpleBlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Adds the specified element as the tail on this queue.
     *
     * @param value the element to add
     */
    public synchronized void offer(T value) {
        while (size() == capacity) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.queue.offer(value);
        notify();
    }

    /**
     * Retrieves and remove the first element of this list.
     *
     * @return the first element of this queue.
     */
    public synchronized T poll() throws InterruptedException {
        while (isEmpty()) {
            wait();
        }
        T result = this.queue.poll();
        notify();
        return result;
    }

    public synchronized boolean isEmpty() {
        return this.queue.isEmpty();
    }

    public synchronized int size() {
        return this.queue.size();
    }
}
