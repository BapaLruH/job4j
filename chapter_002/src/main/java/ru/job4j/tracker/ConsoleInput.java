package ru.job4j.tracker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Сlass ConsoleInput.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 22.11.2018
 */
public class ConsoleInput implements Input {

    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Method ask.
     * Asks a question in the console and reads user input.
     *
     * @param question type String.
     * @return result type String.
     */
    @Override
    public String ask(String question) {
        String result = "";
        System.out.println(question);
        try {
            result = READER.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
