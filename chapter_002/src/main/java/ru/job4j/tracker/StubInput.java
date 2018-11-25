package ru.job4j.tracker;

/**
 * Сlass StubInput.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 22.11.2018
 */
public class StubInput implements Input {
    private final String[] value;
    private int position;

    public StubInput(String[] value) {
        this.value = value;
    }

    /**
     * Method ask.
     * The stub for the tests.
     *
     * @param question type String.
     * @return ask type String.
     */
    @Override
    public String ask(String question) {
        return this.value[position++];
    }

    /**
     * Method ask.
     * The stub for the tests.
     *
     * @param question type String.
     * @param range    type int[].
     * @return ask type String.
     */
    @Override
    public int ask(String question, int[] range) {
        return Integer.valueOf(this.ask(question));
    }
}
