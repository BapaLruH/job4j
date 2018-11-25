package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

/**
 * Сlass StartUI.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 22.11.2018
 */
public class StartUI {
    private final Input input;
    private final Tracker tracker;

    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Method init.
     * Initializes the program.
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        List<Integer> range = new ArrayList<>();
        menu.fillActions();
        for (int i = 0; i < menu.getActionsLentgh(); i++) {
            range.add(i);
        }
        do {
            menu.show();
            try {
                menu.select(Integer.valueOf(this.input.ask("Select:")));
            } catch (NumberFormatException ignored) {
                System.out.println(String.format("Некорректный ввод. Ожидается число от 0 до %s", range.size() - 1));
            }
        } while (!"y".equals(this.input.ask("Exit? (y): ")));
    }

    public static void main(String[] args) {
        StartUI startUI = new StartUI(new ConsoleInput(), new Tracker());
        startUI.init();
    }
}
