package ru.job4j.analyze;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Class Analyze.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 25.12.2018
 */
public class Analyze {

    /**
     * Compare the lists for changes.
     *
     * @param previous list.
     * @param current  list.
     * @return result type Info.
     */
    public Info diff(List<User> previous, List<User> current) {
        Info info = new Info();
        Map<Integer, User> map = previous.stream().collect(Collectors.toMap(v -> v.id, v -> v));
        for (User usr : current) {
            User prUser = map.get(usr.id);
            if (prUser != null && prUser.equals(usr)) {
                map.remove(usr.id);
            } else if (prUser != null && !prUser.equals(usr)) {
                map.remove(usr.id);
                info.changed++;
            } else {
                info.added++;
            }
        }
        info.deleted = map.size();
        return info;
    }

    public static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return id == user.id
                    && Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }

    public static class Info {
        int added;
        int changed;
        int deleted;
    }
}
