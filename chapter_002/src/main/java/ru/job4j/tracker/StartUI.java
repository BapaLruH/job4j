package ru.job4j.tracker;

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
        menu.fillActions();
        int[] range = new int[menu.getActionsLentgh()];
        for (int i = 0; i < menu.getActionsLentgh(); i++) {
            range[i] = i;
        }
        do {
            menu.show();
            menu.select(this.input.ask("Select:", range));
        } while (!"y".equals(this.input.ask("Exit? (y): ")));
    }

    public static void main(String[] args) {
        StartUI startUI = new StartUI(new ValidateInput(), new Tracker());
        startUI.init();
    }
}
