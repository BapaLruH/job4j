package ru.job4j.generics;

/**
 * Сlass UserStore.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 14.12.2018
 */
class UserStore extends AbstractStore<User> {
    UserStore(int size) {
        super(size);
    }
}
