package ru.job4j.threads;

import org.junit.Test;

import java.util.concurrent.Callable;

import static org.junit.Assert.*;

public class UserStorageTest {

    private class ThreadStorage implements Callable<Boolean> {
        private final UserStorage storage;

        private ThreadStorage(Storage storage) {
            this.storage = (UserStorage) storage;
        }

        @Override
        public Boolean call() {
            boolean result = false;
            User userFirst = new User(1, 100);
            User userSecond = new User(2, 50);
            if (storage.add(userFirst) & storage.add(userSecond)) {
                result = storage.transfer(1, 2, 75);
            }
            return result;
        }
    }

    @Test
    public void whenCreateTwoThreadsAndManipulateWithData() {
        Storage storage = new UserStorage();
        ThreadStorage threadStorageFirst = new ThreadStorage(storage);
        ThreadStorage threadStorageSecond = new ThreadStorage(storage);
        var firstResult = threadStorageFirst.call();
        var secondResult = threadStorageSecond.call();
        assertTrue(firstResult);
        assertTrue(secondResult);
    }
}