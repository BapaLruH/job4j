package ru.job4j.threads;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(50);
    private static final int SIZE = Runtime.getRuntime().availableProcessors();

    public ThreadPool() {
        for (int i = 0; i < SIZE; i++) {
            WorkerThread workerThread = new WorkerThread();
            threads.add(workerThread);
            workerThread.start();
        }
    }

    public void work(Runnable task) {
        tasks.offer(task);
    }

    public void shutdown() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    private class WorkerThread extends Thread {
        @Override
        public void run() {
            Runnable task;
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    task = tasks.poll();
                    task.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
