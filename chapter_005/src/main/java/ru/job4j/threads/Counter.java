package ru.job4j.threads;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Class Counter.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 29.12.2018
 */
@ThreadSafe
public class Counter {
    @GuardedBy("this")
    private int count;

    public synchronized void increment() {
            this.count++;
    }

    public synchronized int getCount() {
        return this.count;
    }
}
