package ru.job4j.generics;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserStoreTest {
    private UserStore userStore;

    @Before
    public void setUp() {
        userStore = new UserStore(10);
        userStore.add(new User("1"));
        userStore.add(new User("2"));
        userStore.add(new User("3"));
        userStore.add(new User("4"));
        userStore.add(new User("5"));
    }

    @Test
    public void whenAddNewUserThenStoreContainThisUser() {
        User user = new User("new id");
        userStore.add(user);
        assertThat(userStore.findById("new id"), is(user));
    }

    @Test
    public void whenReplaceUserWithId1() {
        User user = new User("new id");
        userStore.replace("1", user);
        assertThat(userStore.findById("new id"), is(user));
    }

    @Test
    public void whenDeleteUserWithId5() {
        userStore.delete("5");
        assertNull(userStore.findById("5"));
    }

    @Test
    public void whenSearchUserById() {
        User user = new User("new id");
        userStore.add(user);
        userStore.add(new User("6"));
        userStore.add(new User("7"));
        assertThat(userStore.findById("new id"), is(user));
    }
}