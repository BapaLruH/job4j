package ru.job4j.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Сlass MemoryStore.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 05.05.2019
 */
public class MemoryStore implements Store {

    private static volatile MemoryStore instance;
    private final Map<Integer, User> users;
    private volatile AtomicInteger currentId;

    private MemoryStore() {
        users = new ConcurrentHashMap<>();
        currentId = new AtomicInteger(0);
    }

    /**
     * Returns an instance of this object or creates a new.
     *
     * @return instance of this object
     */
    public static MemoryStore getInstance() {
        MemoryStore localInstance = instance;
        if (localInstance == null) {
            synchronized (MemoryStore.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = new MemoryStore();
                    localInstance = instance;
                }
            }
        }
        return localInstance;
    }

    /**
     * Adds a new user to the storage.
     *
     * @param user type User
     * @return {@code true} if a user has been added or {@code false}
     */
    @Override
    public boolean add(User user) {
        boolean rsl = false;
        if (user.getId() == 0) {
            int userId = currentId.getAndIncrement();
            user.setId(userId);
            users.put(userId, user);
            rsl = true;
        } else if (!users.containsValue(user)) {
            users.put(user.getId(), user);
            rsl = true;
        }
        return rsl;
    }

    /**
     * Updates the user from the storage with the specified id.
     *
     * @param id   type int
     * @param user type User
     * @return {@code true} if a user has been updated or {@code false}
     */
    @Override
    public boolean update(int id, User user) {
        boolean rsl = false;
        if (users.containsKey(id) && !users.get(id).equals(user)) {
            users.put(id, user);
            rsl = true;
        }
        return rsl;
    }

    /**
     * Deletes the user from the storage with the specified id.
     *
     * @param id type int
     * @return {@code true} if a user has been deleted or {@code false}
     */
    @Override
    public boolean delete(int id) {
        return Objects.nonNull(users.remove(id));
    }

    /**
     * Returns all entries.
     *
     * @return {@code List<User>}
     */
    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    /**
     * Returns the record from the storage with the specified id
     *
     * @param id type int
     * @return {@code User} if a user has been found or {@code null}
     */
    @Override
    public User findById(int id) {
        return users.get(id);
    }
}
