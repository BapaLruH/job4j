package ru.job4j.generics;

/**
 * Сlass AbstractStore.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 14.12.2018
 */
public abstract class AbstractStore<T extends Base> implements Store<T> {
    private SimpleArray<T> store;

    AbstractStore(int size) {
        this.store = new SimpleArray<>(size);
    }

    @Override
    public void add(T model) {
        this.store.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean result = false;
        for (int i = 0; i < this.store.size(); i++) {
            if (this.store.get(i).getId().equals(id)) {
                this.store.set(i, model);
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        for (int i = 0; i < this.store.size(); i++) {
            if (this.store.get(i).getId().equals(id)) {
                this.store.remove(i);
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public T findById(String id) {
        T result = null;
        for (int i = 0; i < this.store.size(); i++) {
            if (this.store.get(i).getId().equals(id)) {
                result = this.store.get(i);
                break;
            }
        }
        return result;
    }
}
