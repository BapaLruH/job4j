package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

/**
 * Сlass MenuTracker.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 25.11.2018
 */
public class MenuTracker {
    private Input input;
    private Tracker tracker;
    private List<UserAction> actions = new ArrayList<>();

    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public int getActionsLentgh() {
        return this.actions.size();
    }

    public void fillActions() {
        this.actions.add(new AddItem());
        this.actions.add(new ShowItems());
        this.actions.add(new MenuTracker.EditItem());
        this.actions.add(new MenuTracker.DeleteItem());
        this.actions.add(new FindItemById());
        this.actions.add(new FindItemsByName());
    }

    public void select(int key) {
        this.actions.get(key).execute(input, tracker);
    }

    /**
     * Method show.
     * Shows the menu in the console.
     */
    public void show() {
        for (int i = 0; i < actions.size(); i++) {
            UserAction action = actions.get(i);
            if (action != null) {
                System.out.println(i + ". " + action.info());
            }
        }
    }

    /**
     * Class EditItem.
     * Read user input and update item with id(id).
     */
    private static class EditItem implements UserAction {
        @Override
        public int key() {
            return 2;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Изменение заявки --------------");
            String id = input.ask("Введите Id заявки :");
            String name = input.ask("Введите новое имя заявки :");
            String desc = input.ask("Введите новое описание заявки :");
            Item item = new Item(name, desc);
            if (tracker.replace(id, item)) {
                System.out.println("------------ Заявка с Id : " + id + " изменена -----------");
            } else {
                System.out.println("------------ Заявка с Id : " + id + " отсутствует ------------");
            }
        }

        @Override
        public String info() {
            return "Edit item";
        }
    }

    /**
     * Class DeleteItem.
     * Read user input and delete item with id(id).
     */
    private static class DeleteItem implements UserAction {
        @Override
        public int key() {
            return 3;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Удаление заявки --------------");
            String id = input.ask("Введите Id заявки :");
            if (tracker.delete(id)) {
                System.out.println("------------ Заявка с Id : " + id + " удалена -----------");
            } else {
                System.out.println("------------ Заявка с Id : " + id + " отсутствует ------------");
            }
        }

        @Override
        public String info() {
            return "Delete item";
        }
    }

}

/**
 * Class AddItem.
 * Read user input and create a new item.
 */
class AddItem implements UserAction {
    @Override
    public int key() {
        return 0;
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("------------ Добавление новой заявки --------------");
        String name = input.ask("Введите имя заявки :");
        String desc = input.ask("Введите описание заявки :");
        Item item = new Item(name, desc);
        tracker.add(item);
        System.out.println("------------ Новая заявка с getId : " + item.getId() + "-----------");
    }

    @Override
    public String info() {
        return "Add new item";
    }
}

/**
 * Class ShowItems.
 * Shows all elements of the tracker.
 */
class ShowItems implements UserAction {
    @Override
    public int key() {
        return 1;
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        Item[] items = tracker.findAll();
        if (items.length == 0) {
            System.out.println("------------ Данные отсутствуют ------------");
        } else {
            for (Item item : items) {
                System.out.println(item.toString());
            }
        }
    }

    @Override
    public String info() {
        return "Show all items";
    }
}

/**
 * Class FindItemById.
 * Search for an item with an id(id).
 */
class FindItemById implements UserAction {
    @Override
    public int key() {
        return 4;
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("------------ Поиск заявки по Id --------------");
        String id = input.ask("Введите Id заявки :");
        Item result = tracker.findById(id);
        if (result != null) {
            System.out.println(result);
        } else {
            System.out.println("------------ Заявка с Id : " + id + " отсутствует ------------");
        }
    }

    @Override
    public String info() {
        return "Find item by Id";
    }
}

/**
 * Class FindItemsByName.
 * Search for an items with a name(key).
 */
class FindItemsByName implements UserAction {
    @Override
    public int key() {
        return 5;
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("------------ Поиск заявки по имени --------------");
        String name = input.ask("Введите имя заявки :");
        Item[] result = tracker.findByName(name);
        if (result.length == 0) {
            System.out.println("------------ Заявки с именем : " + name + " отсутствуют ------------");
        } else {
            System.out.println("------------ Найденные заявки с именем : " + name + " -----------");
            for (Item item : result) {
                System.out.println(item);
            }
        }
    }

    @Override
    public String info() {
        return "Find items by name";
    }
}

