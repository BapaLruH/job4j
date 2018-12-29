package ru.job4j.threads;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;

@ThreadSafe
public class UserStorage implements Storage {
    @GuardedBy("this")
    private HashMap<Integer, User> users = new HashMap<>();

    /**
     * Adding a user account.
     *
     * @param user type User
     * @return result type boolean
     */
    public synchronized boolean add(User user) {
        User usr = users.putIfAbsent(user.getId(), user);
        boolean result = false;
        if (usr == null) {
            result = users.get(user.getId()).equals(user);
        } else if (user.equals(usr)) {
            result = update(user);
        }
        return result;
    }

    /**
     * Update the user account.
     *
     * @param user type User
     * @return result type boolean
     */
    public synchronized boolean update(User user) {
        User usr = users.replace(user.getId(), user);
        return user.equals(usr);
    }

    /**
     * Delete the user account.
     *
     * @param user type User
     * @return result type boolean
     */
    public synchronized boolean delete(User user) {
        User usr = users.remove(user.getId());
        return user.equals(usr);
    }

    /**
     * Money transfer from fromId to toId. If the transfer is complete {@code true}, else {@code false}.
     *
     * @param fromId type int.
     * @param toId   type int.
     * @param amount type int.
     * @return result type boolean.
     */
    public synchronized boolean transfer(int fromId, int toId, int amount) {
        User from = users.get(fromId);
        User to = users.get(toId);
        boolean result = false;
        if (from.getAmount() >= amount) {
            from.setAmount(from.getAmount() - amount);
            to.setAmount(to.getAmount() + amount);
            result = true;

        }
        return result;
    }
}
