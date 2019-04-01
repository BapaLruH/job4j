package ru.job4j.nonblockingalgorithm;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Class ModelStorageCache.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 29.03.2019
 */
public class ModelStorageCache implements Cache {

    private final ConcurrentHashMap<Integer, Base> cacheStorage = new ConcurrentHashMap<>();

    @Override
    public void add(Base model) {
        cacheStorage.putIfAbsent(model.getId(), model);
    }

    @Override
    public Base get(int id) {
        return cacheStorage.get(id);
    }

    @Override
    public void update(Base model) {
        cacheStorage.computeIfPresent(model.getId(), (id, oldValue) -> {
            if (model.getVersion() != oldValue.getVersion()) {
                throw new OptimisticException();
            }
            int lastVersion = model.getVersion();
            model.setVersion(lastVersion + 1);
            return model;
        });
    }

    @Override
    public void delete(Base model) {
        cacheStorage.remove(model.getId());
    }
}
