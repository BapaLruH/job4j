package ru.job4j.generics;

/**
 * Сlass UserStore.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 14.12.2018
 */
class RoleStore extends AbstractStore<Role> {
    RoleStore(int size) {
        super(size);
    }
}
