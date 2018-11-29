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
        this.actions.add(new AddItem(0, "Add new item"));
        this.actions.add(new ShowItems(1, "Show all items"));
        this.actions.add(new MenuTracker.EditItem(2, "Edit item"));
        this.actions.add(new MenuTracker.DeleteItem(3, "Delete item"));
        this.actions.add(new FindItemById(4, "Find item by Id"));
        this.actions.add(new FindItemsByName(5, "Find items by name"));
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
                System.out.println(action.info());
            }
        }
    }

    /**
     * Class EditItem.
     * Read user input and update item with id(id).
     */
    private static class EditItem extends BaseAction {
        public EditItem(int key, String name) {
            super(key, name);
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
    }

    /**
     * Class DeleteItem.
     * Read user input and delete item with id(id).
     */
    private static class DeleteItem extends BaseAction {
        public DeleteItem(int key, String name) {
            super(key, name);
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
    }

}

/**
 * Class AddItem.
 * Read user input and create a new item.
 */
class AddItem extends BaseAction {
    public AddItem(int key, String name) {
        super(key, name);
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
}

/**
 * Class ShowItems.
 * Shows all elements of the tracker.
 */
class ShowItems extends BaseAction {
    public ShowItems(int key, String name) {
        super(key, name);
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        List<Item> items = tracker.findAll();
        if (items.size() == 0) {
            System.out.println("------------ Данные отсутствуют ------------");
        } else {
            for (Item item : items) {
                System.out.println(item.toString());
            }
        }
    }
}

/**
 * Class FindItemById.
 * Search for an item with an id(id).
 */
class FindItemById extends BaseAction {
    public FindItemById(int key, String name) {
        super(key, name);
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
}

/**
 * Class FindItemsByName.
 * Search for an items with a name(key).
 */
class FindItemsByName extends BaseAction {
    public FindItemsByName(int key, String name) {
        super(key, name);
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("------------ Поиск заявки по имени --------------");
        String name = input.ask("Введите имя заявки :");
        List<Item> result = tracker.findByName(name);
        if (result.size() == 0) {
            System.out.println("------------ Заявки с именем : " + name + " отсутствуют ------------");
        } else {
            System.out.println("------------ Найденные заявки с именем : " + name + " -----------");
            for (Item item : result) {
                System.out.println(item);
            }
        }
    }
}

