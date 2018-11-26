package ru.job4j.tracker;

/**
 * Сlass ValidateInput.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 25.11.2018
 */
public class ValidateInput implements Input {

    private final Input input;

    public ValidateInput(final Input input) {
        this.input = input;
    }

    @Override
    public String ask(String question) {
        return this.input.ask(question);
    }

    @Override
    public int ask(String question, int[] range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = this.input.ask(question, range);
                invalid = false;
            } catch (MenuOutException moe) {
                System.out.println("Please select key from menu! 0 - " + (range.length - 1));
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter validate data again!");
            }
        } while (invalid);
        return value;
    }
}
