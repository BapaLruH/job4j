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
    private final ITracker tracker;

    public StartUI(Input input, ITracker tracker) {
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
        int[] range = new int[menu.getActionsLength()];
        for (int i = 0; i < menu.getActionsLength(); i++) {
            range[i] = i;
        }
        do {
            menu.show(System.out::println);
            menu.select(this.input.ask("Select:", range));
        } while (!"y".equals(this.input.ask("Exit? (y): ")));
    }

    public static void main(String[] args) {
        StartUI startUI = new StartUI(
                new ValidateInput(
                        new ConsoleInput()
                ),
                new Tracker());
        startUI.init();
    }
}
