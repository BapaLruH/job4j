package ru.job4j.email;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class EmailNotificationTest {
    private final PrintStream stout = System.out;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(outputStream));
    }

    @After
    public void backOutput() {
        System.setOut(stout);
    }

    @Test
    public void whenSendOneNotification() {
        EmailNotification notification = new EmailNotification();
        User user = new User("Ivan", "123@u.ru");
        notification.emailTo(user);
        String result = new String(outputStream.toByteArray());
        assertThat(result, is(new StringJoiner(System.lineSeparator())
                .add(String.format("Notification %s to email %s.", user.getName(), user.getEmail()))
                .add(String.format("Add a new event to %s", user.getName()))
                .add(user.getEmail())
                .add("")
                .toString()
        ));
        notification.close();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenSendManyNotifications() throws InterruptedException {
        EmailNotification notification = new EmailNotification();
        StringJoiner stringJoiner = new StringJoiner(System.lineSeparator());
        for (int i = 0; i < 5; i++) {
            notification.emailTo(new User("Name_" + i, "mail" + i + "@u.ru"));
            stringJoiner
                    .add(String.format("Notification %s to email %s.", "Name_" + i, "mail" + i + "@u.ru"))
                    .add(String.format("Add a new event to %s", "Name_" + i))
                    .add("mail" + i + "@u.ru");
            Thread.sleep(100);
        }
        String result = new String(outputStream.toByteArray());
        assertThat(result, is(stringJoiner.add("").toString()));
        notification.close();
        Thread.sleep(1000);

    }
}