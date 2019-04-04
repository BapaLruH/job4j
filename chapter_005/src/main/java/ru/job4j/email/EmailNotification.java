package ru.job4j.email;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Class EmailNotification.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 04.04.2019
 */
public class EmailNotification {

    private final ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    /**
     * Creates a notification and sends it.
     *
     * @param user who receives this notification.
     */
    public void emailTo(User user) {
        service.submit(() -> {
            String subject = String.format("Notification %s to email %s.", user.getName(), user.getEmail());
            String body = String.format("Add a new event to %s", user.getName());
            send(subject, body, user.getEmail());
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void send(String subject, String body, String email) {
        System.out.println(subject + System.lineSeparator() + body + System.lineSeparator() + email);
    }

    /**
     * Closes this service.
     */
    public void close() {
        service.shutdown();
    }
}
