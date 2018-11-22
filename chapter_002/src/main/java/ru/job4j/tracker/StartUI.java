package ru.job4j.tracker;

/**
 * Сlass StartUI.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 22.11.2018
 */
public class StartUI {
    private static final String ADD_NEW_ITEM = "0";
    private static final String SHOW_ALL_ITEM = "1";
    private static final String EDIT_ITEM = "2";
    private static final String DELETE_ITEM = "3";
    private static final String FIND_ITEM_BY_ID = "4";
    private static final String FIND_ITEM_BY_NAME = "5";
    private static final String EXIT = "6";

    private final ConsoleInput input;
    private final Tracker tracker;

    public StartUI(ConsoleInput input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Method init.
     * Initializes the program.
     */
    public void init() {
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Введите пункт меню : ");
            switch (answer) {
                case ADD_NEW_ITEM:
                    this.createItem();
                    break;
                case SHOW_ALL_ITEM:
                    this.showAllItem();
                    break;
                case EDIT_ITEM:
                    this.editItem();
                    break;
                case DELETE_ITEM:
                    this.deleteItem();
                    break;
                case FIND_ITEM_BY_ID:
                    this.findItemById();
                    break;
                case FIND_ITEM_BY_NAME:
                    this.findItemByName();
                    break;
                case EXIT:
                    exit = true;
                    break;
                default:
                    System.out.println("---------------- Некорректный ввод ----------------");
                    break;
            }
        }
    }

    /**
     * Method showMenu.
     * Shows the menu in the console.
     */
    private void showMenu() {
        System.out.println("Menu");
        System.out.println("0. Add new Item");
        System.out.println("1. Show all items");
        System.out.println("2. Edit item");
        System.out.println("3. Delete item");
        System.out.println("4. Find item by Id");
        System.out.println("5. Find items by name");
        System.out.println("6. Exit Program");
        System.out.println("Select:");
    }

    /**
     * Method createItem.
     * Read user input and create a new item.
     */
    private void createItem() {
        System.out.println("------------ Добавление новой заявки --------------");
        String name = this.input.ask("Введите имя заявки :");
        String desc = this.input.ask("Введите описание заявки :");
        Item item = new Item(name, desc);
        this.tracker.add(item);
        System.out.println("------------ Новая заявка с getId : " + item.getId() + "-----------");
    }

    /**
     * Method showAllItem.
     * Shows all elements of the tracker.
     */
    private void showAllItem() {
        Item[] items = this.tracker.findAll();
        if (items.length == 0) {
            System.out.println("------------ Данные отсутствуют ------------");
        } else {
            for (Item item : items) {
                System.out.println(item.toString());
            }
        }
    }

    /**
     * Method editItem.
     * Read user input and update item with id(id).
     */
    private void editItem() {
        System.out.println("------------ Изменение заявки --------------");
        String id = this.input.ask("Введите Id заявки :");
        String name = this.input.ask("Введите новое имя заявки :");
        String desc = this.input.ask("Введите новое описание заявки :");
        Item item = new Item(name, desc);
        if (this.tracker.replace(id, item)) {
            System.out.println("------------ Заявка с Id : " + id + " изменена -----------");
        } else {
            System.out.println("------------ Заявка с Id : " + id + " отсутствует ------------");
        }
    }

    /**
     * Method deleteItem.
     * Read user input and delete item with id(id).
     */
    private void deleteItem() {
        System.out.println("------------ Удаление заявки --------------");
        String id = this.input.ask("Введите Id заявки :");
        if (this.tracker.delete(id)) {
            System.out.println("------------ Заявка с Id : " + id + " удалена -----------");
        } else {
            System.out.println("------------ Заявка с Id : " + id + " отсутствует ------------");
        }
    }

    /**
     * Method findItemById.
     * Search for an item with an id(id).
     */
    private void findItemById() {
        System.out.println("------------ Поиск заявки по Id --------------");
        String id = this.input.ask("Введите Id заявки :");
        Item result = this.tracker.findById(id);
        if (result != null) {
            System.out.println(result);
        } else {
            System.out.println("------------ Заявка с Id : " + id + " отсутствует ------------");
        }
    }

    /**
     * Method findItemByName.
     * Search for an items with a name(key).
     */
    private void findItemByName() {
        System.out.println("------------ Поиск заявки по имени --------------");
        String key = this.input.ask("Введите имя заявки :");
        Item[] result = this.tracker.findByName(key);
        if (result.length == 0) {
            System.out.println("------------ Заявки с именем : " + key + " отсутствуют ------------");
        } else {
            System.out.println("------------ Найденные заявки с именем : " + key + " -----------");
            for (Item item : result) {
                System.out.println(item);
            }
        }
    }

    public static void main(String[] args) {
        StartUI startUI = new StartUI(new ConsoleInput(), new Tracker());
        startUI.init();
    }
}
